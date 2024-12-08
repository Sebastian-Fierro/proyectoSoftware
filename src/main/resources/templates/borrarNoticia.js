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

async function borrarNoticia(id, usuarioId) {
    try {
        const response = await fetch(`http://localhost:8080/noticia/${id}?usuarioId=${usuarioId}`, {
            method: "DELETE",
        });

        if (!response.ok) {
            throw new Error("No se pudo borrar la noticia.");
        }

        alert("Noticia borrada con éxito.");
        document.querySelector(`.noticia[data-id="${id}"]`).remove(); // Elimina la noticia del DOM
    } catch (error) {
        alert(`Error al borrar la noticia: ${error.message}`);
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
        noticiaElement.setAttribute("data-id", noticia.idNoticia);

        let creador = "Desconocido";
        if (noticia.usuarios.length > 0) {
            creador = noticia.usuarios[0].nombre; // Accede directamente al nombre del usuario
        }

        noticiaElement.innerHTML = `
            <h2 class="title">${noticia.titulo}</h2>
            <p class="content">${noticia.contenido}</p>
            <p class="meta">Creada el: ${new Date(noticia.createdAt).toLocaleString()}</p>
            <p class="meta">Creado por: ${creador}</p>
            <div class="actions">
                <button class="delete-button" data-id="${noticia.idNoticia}">Borrar</button>
            </div>
        `;

        container.appendChild(noticiaElement);
    }

    // Agrega eventos a los botones de borrar
    document.querySelectorAll(".delete-button").forEach(button => {
        button.addEventListener("click", async (event) => {
            const id = button.getAttribute("data-id");
            const usuarioId = prompt("Ingrese su ID de usuario:");

            if (!usuarioId) {
                alert("El ID de usuario es obligatorio.");
                return;
            }

            const confirmDelete = confirm("¿Está seguro de que desea borrar esta noticia?");
            if (!confirmDelete) return;

            // Llama a la función para enviar la solicitud de borrado
            await borrarNoticia(id, usuarioId);
        });
    });
}

// Llamar a la función al cargar la página
fetchNoticias();