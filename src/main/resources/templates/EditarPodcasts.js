document.addEventListener('DOMContentLoaded', function() {
    const podcastForm = document.getElementById('podcast-form');
    podcastForm.addEventListener('submit', function(event) {
        event.preventDefault();

        const nombre = document.getElementById('nombre').value;
        const tipo = document.getElementById('tipo').value;
        const url = document.getElementById('url').value;

        fetch('/api/podcasts', {
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
            alert('Podcast agregado exitosamente');
            loadPodcasts();
        })
        .catch(error => console.error('Error al agregar podcast:', error));
    });

    function loadPodcasts() {
        fetch('/api/podcasts')
            .then(response => response.json())
            .then(data => {
                const podcastsList = document.getElementById('podcasts-list');
                podcastsList.innerHTML = '';
                data.forEach(podcast => {
                    const podcastItem = document.createElement('div');
                    podcastItem.classList.add('podcast-item');
                    podcastItem.innerHTML = `
                        <h2>${podcast.nombre}</h2>
                        <p>${podcast.tipo}</p>
                        <a href="${podcast.url}" target="_blank">Escuchar Podcast</a>
                    `;
                    podcastsList.appendChild(podcastItem);
                });
            })
            .catch(error => console.error('Error al cargar los podcasts:', error));
    }

    loadPodcasts();
});
