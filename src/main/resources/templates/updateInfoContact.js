document.getElementById("editContactForm").addEventListener("submit", function (event) {
    event.preventDefault(); // Evita la recarga de la página

    // Recopilar los datos del formulario
    const updatedContact = {
        correo: document.getElementById("correo").value,
        telefono: document.getElementById("telefono").value,
        facebook: document.getElementById("facebook").value,
        instagram: document.getElementById("instagram").value
    };

    // Enviar los datos al backend (simulación de solicitud)
    fetch("http://localhost:8080/infoContacto/update", {
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
});
