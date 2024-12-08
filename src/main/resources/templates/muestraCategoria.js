window.onload = function(){
    listarCategorias();
};


//OBTIENE CATEGORIAS

let listarCategorias = async()=>{
    const peticion = await fetch("http://localhost:8080/categorias",
    {
        method:  'GET',
        headers:{
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        }

    });

    const categorias = await peticion.json();

    let contenidoTabla = "";

    for(const categoria of categorias){
       let contenidoFila = `<tr>
       <td>${categoria.idCategory}</td>
       <td>${categoria.nombre}</td>
       <td>
       <i onClick="editarCategoria(${categoria.idCategory})" class="material-icons button edit">editar</i>
       <i onClick="borrarCategoria(${categoria.idCategory})" class="material-icons button delete">borrar</i>
       </td>
       </tr>`
       contenidoTabla += contenidoFila;
    }

    document.querySelector("#tabla tbody").outerHTML = contenidoTabla;

}

//BORRAR CATEGORIA
let  borrarCategoria = async(id)=>{
    const peticion = await fetch("http://localhost:8080/categorias/"+id,
        {
            method:  'DELETE',
            headers:{
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            }
    
        });

    listarCategorias();
}


//MODIFICAR
let idEditar;

let editarCategoria = async (id)=>{
    
    mostrarModificar();

    idEditar = id;

    const peticion = await fetch("http://localhost:8080/categorias/"+id,
    {
        method:  'GET',
        headers:{
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            }
    });

    const categoria = await peticion.json();

    document.getElementById("nombre").value=categoria.nombre; 

    let btnModificar = document.getElementById("btnModificar");
}

btnModificar.addEventListener("click", evento=>{
    aplicarActualizacion(idEditar);
})

let aplicarActualizacion = async(id) =>{
    let campos = {};
    campos.id = id;
    campos.nombre = document.getElementById("nombre").value;

    const peticion = await fetch("http://localhost:8080/categorias/"+id,
        {
            method: 'PUT',
            headers: {
                'Accept': 'application/json',
                "Content-Type": "application/json",
            },
            body:  JSON.stringify(campos)
        });


        listarCategorias();
}



function mostrarModificar(){
    let formulario = document.getElementById("formulario").style.visibility="visible";
    document.getElementsByTagName("h1")[0].style.visibility="visible";
}