document.getElementById('crearUsuarioForm').addEventListener('submit', async (e) => {
    e.preventDefault();

    // Obtener los valores del formulario
    const nombre = document.getElementById('nombre').value.trim();
    const contraseña = document.getElementById('contraseña').value.trim();
    const correo = document.getElementById('correo').value.trim();
    const rol = parseInt(document.getElementById('rol').value);

    const mensaje = document.getElementById('mensaje');
    mensaje.textContent = '';
    mensaje.className = '';

    // Validación de campos
    if (!nombre || !contraseña || !correo || !rol) {
        mensaje.textContent = 'El nombre, la contraseña, el correo y el rol no pueden estar vacíos.';
        mensaje.className = 'error';
        return;
    }

    try {
        // Obtener la fecha de creación automáticamente
        const created_at = new Date().toISOString();

        // Solicitud al servidor
        const response = await fetch('http://localhost:8080/usuario', {  // Cambié la URL a la correcta
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify({
                nombre,
                contraseña,
                correo,
                created_at,
                rol: { id_rol: rol }
            }),
        });

        // Manejo de respuestas
        if (response.status === 201) {
            const data = await response.json();
            mensaje.textContent = '¡Usuario creado exitosamente!';
            mensaje.className = 'success';
            console.log('Usuario creado:', data);
        } else {
            const errorText = await response.text();
            mensaje.textContent = errorText || 'Error al crear el usuario.';
            mensaje.className = 'error';
        }
    } catch (error) {
        mensaje.textContent = 'No se pudo conectar con el servidor.';
        mensaje.className = 'error';
        console.error('Error:', error);
    }
});
