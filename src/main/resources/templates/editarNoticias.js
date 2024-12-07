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

async function editarNoticia(id, usuarioId, nuevoTitulo, nuevoContenido) {
    try {
        const response = await fetch(`http://localhost:8080/noticia/${id}?usuarioId=${usuarioId}`, {
            method: "PUT",
            headers: {
                "Content-Type": "application/json",
            },
            body: JSON.stringify({
                titulo: nuevoTitulo,
                contenido: nuevoContenido,
            }),
        });

        if (!response.ok) {
            throw new Error("No se pudo editar la noticia.");
        }

        alert("Noticia editada con éxito.");
    } catch (error) {
        alert(`Error al editar la noticia: ${error.message}`);
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

        let creador = "Desconocido";
        if (noticia.usuarios.length > 0) {
            creador = noticia.usuarios[0].nombre; // Accede directamente al nombre del usuario
        }

        noticiaElement.innerHTML = `
            <h2 class="editable title" contenteditable="true">${noticia.titulo}</h2>
            <p class="editable content" contenteditable="true">${noticia.contenido}</p>
            <p class="meta">Creada el: ${new Date(noticia.createdAt).toLocaleString()}</p>
            <p class="meta">Creado por: ${creador}</p>
            <div class="actions">
                <button class="save-button" data-id="${noticia.idNoticia}">Guardar cambios</button>
            </div>
        `;

        container.appendChild(noticiaElement);
    }

    // Agrega eventos a los botones de guardar
    document.querySelectorAll(".save-button").forEach(button => {
        button.addEventListener("click", async (event) => {
            const id = button.getAttribute("data-id");
            const noticiaElement = button.closest(".noticia");
            const nuevoTitulo = noticiaElement.querySelector(".title").textContent.trim();
            const nuevoContenido = noticiaElement.querySelector(".content").textContent.trim();
            const usuarioId = prompt("Ingrese su ID de usuario:");

            if (!usuarioId) {
                alert("El ID de usuario es obligatorio.");
                return;
            }

            if (!nuevoTitulo || !nuevoContenido) {
                alert("El título y el contenido no pueden estar vacíos.");
                return;
            }

            // Llama a la función para enviar la solicitud de edición
            await editarNoticia(id, usuarioId, nuevoTitulo, nuevoContenido);
        });
    });
}

// Llamar a la función al cargar la página
fetchNoticias();
