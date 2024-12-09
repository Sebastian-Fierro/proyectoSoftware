document.addEventListener("DOMContentLoaded", function() {
    fetch('/multimedia')
        .then(response => response.json())
        .then(data => {
            const multimediaList = document.getElementById('multimedia-list');
            data.forEach(item => {
                const div = document.createElement('div');
                div.classList.add('multimedia-item');
                div.innerHTML = `
                    <h3>${item.nombre}</h3>
                    <p>Tipo: ${item.tipo}</p>
                    <a href="${item.url}" target="_blank">Ver contenido</a>
                    <button onclick="editMultimedia(${item.idMult})">Editar</button>
                    <button onclick="deleteMultimedia(${item.idMult})">Eliminar</button>
                `;
                multimediaList.appendChild(div);
            });
        })
        .catch(error => console.error('Error fetching multimedia:', error));
});

function editMultimedia(id) {
    const newName = prompt('Introduce el nuevo nombre del contenido:');
    const newUrl = prompt('Introduce la nueva URL del contenido:');
    const newType = prompt('Introduce el nuevo tipo del contenido (podcast, video, etc.):');
    
    if (newName && newUrl && newType) {
        fetch(`/multimedia/${id}`, {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json',
                'Authorization': `Bearer ${sessionStorage.getItem('token')}` // Asumiendo que el token se guarda en sessionStorage
            },
            body: JSON.stringify({
                nombre: newName,
                tipo: newType,
                url: newUrl
            })
        })
        .then(response => {
            if (response.ok) {
                alert('Contenido actualizado correctamente');
                location.reload();
            } else {
                alert('Error al actualizar el contenido');
            }
        })
        .catch(error => console.error('Error al editar multimedia:', error));
    }
}

function deleteMultimedia(id) {
    fetch(`/multimedia/${id}`, {
        method: 'DELETE',
        headers: {
            'Content-Type': 'application/json',
            'Authorization': `Bearer ${sessionStorage.getItem('token')}` // Asumiendo que el token se guarda en sessionStorage
        }
    })
    .then(response => {
        if (response.ok) {
            alert('Multimedia eliminado correctamente');
            location.reload();
        } else {
            alert('Error al eliminar multimedia');
        }
    })
    .catch(error => console.error('Error al eliminar multimedia:', error));
}
