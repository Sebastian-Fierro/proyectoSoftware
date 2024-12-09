document.getElementById('componentForm').addEventListener('submit', async (e) => {
    e.preventDefault();

    const data = {
        nombre: document.getElementById('nombre').value,
        descripcion: document.getElementById('descripcion').value
    };

    try {
        const response = await fetch('http://localhost:8080/componentes', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(data)
        });

        if (response.ok) {
            const result = await response.json();
            document.getElementById('response').textContent = '¡Componente agregado con éxito!';
            document.getElementById('response').style.color = 'green';
        } else {
            document.getElementById('response').textContent = 'Error al agregar el componente.';
            document.getElementById('response').style.color = 'red';
        }
    } catch (error) {
        document.getElementById('response').textContent = 'Hubo un problema al conectarse con el servidor.';
        document.getElementById('response').style.color = 'red';
    }
});
