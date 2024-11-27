document.getElementById('crearNoticiaForm').addEventListener('submit', async (e) => {
    e.preventDefault();

    // Obtener los valores del formulario
    const titulo = document.getElementById('titulo').value.trim();
    const contenido = document.getElementById('contenido').value.trim();
    const usuarioId = parseInt(document.getElementById('usuarioId').value);

    const mensaje = document.getElementById('mensaje');
    mensaje.textContent = '';
    mensaje.className = '';

    // Validación de campos
    if (!titulo || !contenido) {
        mensaje.textContent = 'El título y el contenido no pueden estar vacíos.';
        mensaje.className = 'error';
        return;
    }

    try {
        // Solicitud al servidor
        const response = await fetch(`http://localhost:8080/noticia/crear?usuarioId=${usuarioId}`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify({
                titulo,
                contenido,
            }),
        });

        // Manejo de respuestas
        if (response.status === 201) {
            const data = await response.json();
            mensaje.textContent = '¡Noticia creada exitosamente!';
            mensaje.className = 'success';
            console.log('Noticia creada:', data);
        } else if (response.status === 403) {
            mensaje.textContent = 'No tienes permiso para crear noticias.';
            mensaje.className = 'error';
        } else if (response.status === 400) {
            const errorText = await response.text();
            mensaje.textContent = errorText || 'Error en los datos enviados.';
            mensaje.className = 'error';
        } else {
            mensaje.textContent = 'Error inesperado.';
            mensaje.className = 'error';
        }
    } catch (error) {
        mensaje.textContent = 'No se pudo conectar con el servidor.';
        mensaje.className = 'error';
        console.error('Error:', error);
    }
});
