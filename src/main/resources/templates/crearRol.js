const form = document.getElementById('createRoleForm');
const responseMessage = document.getElementById('responseMessage');

form.addEventListener('submit', async (e) => {
    e.preventDefault();

    const roleName = document.getElementById('roleName').value;
    const checkedPermissions = Array.from(document.querySelectorAll('input[type="checkbox"]:checked')).map(input => ({
        idPermiso: parseInt(input.value),
        nombre: input.dataset.nombre,
        descripcion: input.dataset.descripcion
    }));

    const roleData = {
        nombre: roleName,
        permisos: checkedPermissions
    };

    try {
        const response = await fetch('http://localhost:8080/rol', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(roleData)
        });

        if (response.ok) {
            const result = await response.json();
            responseMessage.textContent = `Rol creado exitosamente: ${result.nombre}`;
            responseMessage.style.color = 'green';
        } else {
            const error = await response.json();
            responseMessage.textContent = `Error al crear el rol: ${error.message || 'Error desconocido'}`;
            responseMessage.style.color = 'red';
        }
    } catch (error) {
        responseMessage.textContent = `Error al conectar con el servidor: ${error.message}`;
        responseMessage.style.color = 'red';
    }
});
