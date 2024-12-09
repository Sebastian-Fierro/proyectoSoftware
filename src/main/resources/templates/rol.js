// URL del endpoint
const endpoint = "http://localhost:8080/rol/";

// Función para cargar roles y mostrarlos
async function loadRoles() {
    const container = document.getElementById("roles-container");

    try {
        const response = await fetch(endpoint);
        if (!response.ok) throw new Error("Error al obtener los roles");

        const roles = await response.json();
        container.innerHTML = ""; // Limpiar contenido previo

        roles.forEach(role => {
            // Crear la tarjeta de cada rol
            const roleCard = document.createElement("div");
            roleCard.className = "role-card";

            // Título del rol
            const roleTitle = document.createElement("h2");
            roleTitle.textContent = role.nombre;

            // Tabla de permisos
            let permissionsTable = `<table>
                <thead>
                    <tr>
                        <th>Nombre</th>
                        <th>Descripción</th>
                    </tr>
                </thead>
                <tbody>`;

            if (role.permisos.length > 0) {
                role.permisos.forEach(permiso => {
                    permissionsTable += `
                        <tr>
                            <td>${permiso.nombre}</td>
                            <td>${permiso.descripcion}</td>
                        </tr>`;
                });
            } else {
                permissionsTable += `
                    <tr>
                        <td colspan="2">Sin permisos asignados</td>
                    </tr>`;
            }
            permissionsTable += `</tbody></table>`;

            // Añadir elementos al card
            roleCard.appendChild(roleTitle);
            roleCard.innerHTML += permissionsTable;
            container.appendChild(roleCard);
        });
    } catch (error) {
        console.error("Error:", error);
        container.innerHTML = `<p>Error al cargar los roles. Intenta de nuevo más tarde.</p>`;
    }
}

// Ejecutar la función al cargar la página
window.onload = loadRoles;
