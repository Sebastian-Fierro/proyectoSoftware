document.addEventListener('DOMContentLoaded', function() {
    const multimediaForm = document.getElementById('multimedia-form');
    multimediaForm.addEventListener('submit', function(event) {
        event.preventDefault();

        const nombre = document.getElementById('nombre').value;
        const tipo = document.getElementById('tipo').value;
        const url = document.getElementById('url').value;

        fetch('/multimedia', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify({
                nombre: nombre,
                tipo: tipo,
                url: url
            })
        })
        .then(response => response.json())
        .then(data => {
            alert('Contenido multimedia agregado exitosamente');
            loadMultimedia();
        })
        .catch(error => console.error('Error al agregar contenido multimedia:', error));
    });

    function loadMultimedia() {
        fetch('/multimedia')
            .then(response => response.json())
            .then(data => {
                const multimediaList = document.getElementById('multimedia-list');
                multimediaList.innerHTML = '';
                data.forEach(item => {
                    const multimediaItem = document.createElement('div');
                    multimediaItem.classList.add('multimedia-item');
                    multimediaItem.innerHTML = `
                        <h2>${item.nombre}</h2>
                        <p>Tipo: ${item.tipo}</p>
                        <a href="${item.url}" target="_blank">Ver Contenido</a>
                        <button onclick="editMultimedia(${item.idMult})">Editar</button>
                        <button onclick="deleteMultimedia(${item.idMult})">Eliminar</button>
                    `;
                    multimediaList.appendChild(multimediaItem);
                });
            })
            .catch(error => console.error('Error al cargar los contenidos multimedia:', error));
    }

    function editMultimedia(id) {
        // Implementar la lógica de edición del contenido multimedia
    }

    function deleteMultimedia(id) {
        fetch(`/multimedia/${id}`, {
            method: 'DELETE'
        })
        .then(() => {
            alert('Contenido multimedia eliminado');
            loadMultimedia();
        })
        .catch(error => console.error('Error al eliminar contenido multimedia:', error));
    }

    loadMultimedia();
});
