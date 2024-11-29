// Función para cargar datos iniciales de contactos
function loadContacts() {
    fetch('/infoContacto')
        .then(response => response.json())
        .then(data => {
            const tableBody = document.getElementById('contactTableBody');
            tableBody.innerHTML = ''; // Limpiar la tabla

            data.forEach(contact => {
                const row = document.createElement('tr');
                row.innerHTML = `
                    <td>${contact.idContact}</td>
                    <td>${contact.correo}</td>
                    <td>${contact.telefono}</td>
                    <td>${contact.instagram}</td>
                    <td>${contact.facebook}</td>
                    <td><button onclick="editContact(${contact.idContact})">Editar</button></td>
                `;
                tableBody.appendChild(row);
            });
        })
        .catch(error => console.error('Error cargando contactos:', error));
}

// Función para manejar el formulario
document.getElementById('infoContactForm').addEventListener('submit', function (event) {
    event.preventDefault();

    const contact = {
        correo: document.getElementById('correo').value,
        telefono: document.getElementById('telefono').value,
        instagram: document.getElementById('instagram').value,
        facebook: document.getElementById('facebook').value
    };

    fetch('/infoContacto', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(contact)
    })
        .then(response => response.json())
        .then(data => {
            console.log('Contacto guardado:', data);
            loadContacts(); // Recarga la lista de contactos
        })
        .catch(error => console.error('Error al guardar contacto:', error));
});

// Carga inicial de contactos
document.addEventListener('DOMContentLoaded', loadContacts);
