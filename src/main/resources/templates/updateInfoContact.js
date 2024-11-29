// URL base para el backend
const API_URL = "/infoContacto";

// Función para cargar contactos (GET)
function loadContacts() {
    fetch(API_URL)
        .then(response => response.json())
        .then(data => {
            const tableBody = document.querySelector("#infoContactTable tbody");
            tableBody.innerHTML = ""; // Limpiar la tabla
            data.forEach(contact => {
                const row = `
                    <tr>
                        <td>${contact.idContact}</td>
                        <td>${contact.correo}</td>
                        <td>${contact.telefono}</td>
                        <td>${contact.instagram}</td>
                        <td>${contact.facebook}</td>
                        <td>
                            <button onclick="editContact(${contact.idContact})">Editar</button>
                        </td>
                    </tr>
                `;
                tableBody.innerHTML += row;
            });
        })
        .catch(error => console.error("Error al cargar contactos:", error));
}

// Función para manejar la edición de un contacto
function editContact(id) {
    fetch(`${API_URL}/${id}`)
        .then(response => response.json())
        .then(contact => {
            document.getElementById("idContact").value = contact.idContact;
            document.getElementById("correo").value = contact.correo;
            document.getElementById("telefono").value = contact.telefono;
            document.getElementById("instagram").value = contact.instagram;
            document.getElementById("facebook").value = contact.facebook;
        })
        .catch(error => console.error("Error al cargar contacto:", error));
}

// Función para actualizar contacto (PUT)
document.getElementById("infoContactForm").addEventListener("submit", function (event) {
    event.preventDefault();

    const id = document.getElementById("idContact").value;
    const updatedContact = {
        correo: document.getElementById("correo").value,
        telefono: document.getElementById("telefono").value,
        instagram: document.getElementById("instagram").value,
        facebook: document.getElementById("facebook").value,
        updated_by: { id_user: 1 } // Cambiar según el usuario actual
    };

    fetch(`${API_URL}/update/${id}`, {
        method: "PUT",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(updatedContact)
    })
        .then(response => response.json())
        .then(data => {
            console.log("Contacto actualizado:", data);
            loadContacts(); // Recargar lista
        })
        .catch(error => console.error("Error al actualizar contacto:", error));
});

// Cargar contactos al inicio
document.addEventListener("DOMContentLoaded", loadContacts);
