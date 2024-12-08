async function fetchNoticias() {
    try {
        const response = await fetch("http://localhost:8080/noticia/"); // Endpoint para obtener todas las noticias
        if (!response.ok) {
            throw new Error("No se pudieron recuperar las noticias");
        }
        const noticias = await response.json(); // Convierte la respuesta a JSON
        displayNoticias(noticias); // Renderiza las noticias
    } catch (error) {
        document.getElementById("error-message").textContent = error.message;
    }
}

function displayNoticias(noticias) {
    const container = document.getElementById("noticias-list");

    if (noticias.length === 0) {
        container.innerHTML = "<p>No hay noticias disponibles.</p>";
        return;
    }

    for (const noticia of noticias) {
        const noticiaElement = document.createElement("div");
        noticiaElement.classList.add("noticia");

        // Verificar si hay un usuario asociado
        let creador = "Desconocido";
        if (noticia.usuarios.length > 0) {
            creador = noticia.usuarios[0].nombre; // Accede directamente al nombre del usuario
        }

        noticiaElement.innerHTML = `
            <h2 class="title">${noticia.titulo}</h2>
            <p class="content">${noticia.contenido}</p>
            <p class="meta">Creada el: ${new Date(noticia.createdAt).toLocaleString()}</p>
            <p class="meta">Creado por: ${creador}</p>
        `;

        container.appendChild(noticiaElement);
    }
}

// Llamar a la función al cargar la página
fetchNoticias();
