const API_URL = "/infoContacto"; // Endpoint de la API

// Cargar la información desde el backend
function loadContactInfo() {
    fetch(API_URL)
        .then(response => response.json())
        .then(data => {
            // Actualiza los elementos visibles en el HTML
            document.getElementById("telefono").textContent = data.telefono;
            document.getElementById("correo").textContent = data.correo;
            document.getElementById("facebook").href = data.facebook;
            document.getElementById("facebook").textContent = "Visitar Facebook";
        })
        .catch(error => console.error("Error cargando información de contacto:", error));
}

// Manejar la edición y actualización (PUT)
document.getElementById("editContactForm").addEventListener("submit", function(event) {
    event.preventDefault();

    const updatedContact = {
        telefono: document.getElementById("newTelefono").value || undefined,
        correo: document.getElementById("newCorreo").value || undefined,
        facebook: document.getElementById("newFacebook").value || undefined,
    };

    fetch(API_URL, {
        method: "PUT",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(updatedContact)
    })
        .then(response => response.json())
        .then(data => {
            alert("Información actualizada exitosamente.");
            loadContactInfo(); // Recargar los datos
        })
        .catch(error => console.error("Error actualizando información:", error));
});

// Carga inicial de datos
document.addEventListener("DOMContentLoaded", loadContactInfo);
