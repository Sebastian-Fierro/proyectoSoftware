document.addEventListener('DOMContentLoaded', function() {
    fetch('/api/podcasts')
        .then(response => response.json())
        .then(data => {
            const podcastsList = document.getElementById('podcasts-list');
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
});
