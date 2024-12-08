document.getElementById('loginForm').addEventListener('submit', async function (event) {
    event.preventDefault();

    const email = document.getElementById('email').value;
    const password = document.getElementById('password').value;
    const errorMessage = document.getElementById('error-message');

    try {
        const response = await fetch('http://localhost:8080/usuario'); // Endpoint de tu API
        const usuarios = await response.json();

        // Validar las credenciales
        const usuario = usuarios.find(user => user.correo === email && user.contraseña === password);

        if (usuario) {
            window.location.href = 'index.html'; // Redirigir a index.html
        } else {
            errorMessage.textContent = 'Correo o contraseña incorrectos';
        }
    } catch (error) {
        errorMessage.textContent = 'Error al conectar con el servidor';
        console.error(error);
    }
});
