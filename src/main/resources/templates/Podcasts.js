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
                `;
                multimediaList.appendChild(div);
            });
        })
        .catch(error => console.error('Error fetching multimedia:', error));
});
