let boton = document.getElementById("btnregistrar");

boton.addEventListener("click", evento=>{
    registrarCategoria();
});

let registrarCategoria = async()=>{
    
let campos = {};

campos.nombre = document.getElementById("nombre").value; //Solo tiene 1 campo

const peticion = await fetch("http://localhost:8080/categorias",
{
    method: 'POST',
    headers: {
        'Accept': 'application/json',
        "Content-Type": "application/json",
    },
    body:  JSON.stringify(campos)
});


}