document.getElementById("updateForm").addEventListener("submit", function (event) {
    event.preventDefault(); // Evita la recarga de la página

    const API_URL = "http://localhost:8080/infoContacto/update/3"; // Reemplaza "1" con el ID correspondiente.

    // Recopilar los datos del formulario
    const updatedContact = {
        correo: document.getElementById("correo").value,
        telefono: document.getElementById("telefono").value,
        facebook: document.getElementById("facebook").value,
        instagram: document.getElementById("instagram").value
    };

    // Enviar los datos al backend (simulación de solicitud)
    fetch(API_URL, {
        method: "PUT",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(updatedContact)
    })
        .then(response => {
            if (response.ok) {
                alert("Información actualizada correctamente.");
            } else {
                alert("Error al actualizar la información.");
            }
        })
        .catch(error => console.error("Error:", error));

        document.addEventListener("DOMContentLoaded", function () {
            const API_URL = "http://localhost:8080/infoContacto/3"; // Reemplaza "1" con el ID correspondiente.
        
            fetch(API_URL)
                .then(response => {
                    if (!response.ok) throw new Error("No se pudo cargar la información.");
                    return response.json();
                })
                .then(data => {
                    document.getElementById("correo").value = data.correo;
                    document.getElementById("telefono").value = data.telefono;
                    document.getElementById("facebook").value = data.facebook;
                    document.getElementById("instagram").value = data.instagram;
                })
                .catch(error => console.error("Error al cargar la información:", error));
        });
        
});
