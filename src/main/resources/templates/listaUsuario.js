window.onload = function() {
    listarUsuarios();
};

// OBTENER USUARIOS
let listarUsuarios = async () => {
    const peticion = await fetch("http://localhost:8080/usuario", {
        method: 'GET',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        }
    });

    const usuarios = await peticion.json();
    let contenidoTabla = "";

    for (const usuario of usuarios) {
        let contenidoFila = `<tr>
            <td>${usuario.id_user}</td>
            <td>${usuario.nombre}</td>
            <td>${usuario.correo}</td>
            <td>${usuario.rol ? usuario.rol.nombre : "Sin rol"}</td>
            <td>
                <i onClick="borrarUsuario(${usuario.id_user})" class="material-icons button delete">borrar</i>
            </td>
        </tr>`;
        contenidoTabla += contenidoFila;
    }

    document.querySelector("#tabla tbody").innerHTML = contenidoTabla;
};

// ELIMINAR USUARIO
let borrarUsuario = async (id) => {
    // Mostrar un cuadro de confirmación antes de proceder
    const confirmacion = confirm("¿Estás seguro de que deseas eliminar este usuario?");
    
    // Si el usuario confirma, se procede con la eliminación
    if (confirmacion) {
        const peticion = await fetch("http://localhost:8080/usuario/usuario/" + id, {
            method: 'DELETE',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            }
        });

        // Después de eliminar el usuario, actualizar la lista
        listarUsuarios();
    }
}

