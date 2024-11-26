document.getElementById('loginForm').addEventListener('submit', function(event) {
    event.preventDefault(); // Evita que el formulario se envíe de la manera tradicional

    const email = document.getElementById('email').value;
    const password = document.getElementById('password').value;

    fetch('http://localhost:8080/usuario/login', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({ email: email, password: password })
    })

    .then(response => {
        if(response.ok){
            return response.json();
        }else if(response.status === 401){
            throw new Error('Correo electrónico o contraseña incorrectos');
        }else{
            throw new Error('Error en la petición');
        }
    })

    .then(data => {
        if (data.success) {
            // Redirigir a la página de inicio o dashboard
            window.location.href = '/dashboard';
        } else {
            // Mostrar mensaje de error
            alert('Correo electrónico o contraseña incorrectos');
        }
    })

    .catch(error => {
        console.error('Error:', error);
    });
});