document.addEventListener("DOMContentLoaded", function () {
    const API_URL_GET = "http://localhost:8080/infoContacto/3"; // Ajusta el ID según corresponda
    const API_URL_PUT = "http://localhost:8080/infoContacto/update/3"; // Ajusta el ID según corresponda
    const API_URL_VALIDATE_USER = "http://localhost:8080/usuario"; // Endpoint para validar usuario

    // Cargar la información del contacto
    fetch(API_URL_GET)
        .then(response => {
            if (!response.ok) throw new Error("No se pudo cargar la información del contacto.");
            return response.json();
        })
        .then(data => {
            document.getElementById("correo").value = data.correo;
            document.getElementById("telefono").value = data.telefono;
            document.getElementById("facebook").value = data.facebook;
            document.getElementById("instagram").value = data.instagram;

            if (data.updated_by) {
                document.getElementById("updated_by").value = data.updated_by.id_user;
            }
        })
        .catch(error => console.error("Error al cargar la información del contacto:", error));

    // Manejar el envío del formulario
    document.getElementById("updateForm").addEventListener("submit", function (event) {
        event.preventDefault(); // Evitar recargar la página

        const userId = document.getElementById("updated_by").value;

        // Validar si el ID del usuario existe en la base de datos
        fetch(`${API_URL_VALIDATE_USER}/${userId}`)
            .then(response => {
                if (!response.ok) throw new Error("Usuario no válido o no encontrado.");
                return response.json();
            })
            .then(user => {
                // Si el usuario existe, recopilar los datos del formulario
                const updatedContact = {
                    correo: document.getElementById("correo").value,
                    telefono: document.getElementById("telefono").value,
                    facebook: document.getElementById("facebook").value,
                    instagram: document.getElementById("instagram").value,
                    updated_by: { id_user: user.id_user }
                };

                // Enviar los datos al backend
                fetch(API_URL_PUT, {
                    method: "PUT",
                    headers: { "Content-Type": "application/json" },
                    body: JSON.stringify(updatedContact)
                })
                    .then(response => {
                        if (response.ok) {
                            alert("Información actualizada correctamente.");
                            window.location.href = "mostrarInfoContact.html"; // Redirigir al mostrarInfoContact
                        } else {
                            alert("Error al actualizar la información.");
                        }
                    })
                    .catch(error => console.error("Error al actualizar la información:", error));
            })
            .catch(error => {
                alert(error.message);
                console.error("Error al validar el usuario:", error);
            });
    });
});
