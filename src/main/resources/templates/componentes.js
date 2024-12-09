window.onload = function() {
    fetch("http://localhost:8080/componentes/") // Cambia la URL si es necesario
        .then(response => response.json())
        .then(data => {
            const departamentosContainer = document.querySelector("#departamentosContainer");
            departamentosContainer.innerHTML = ""; // Limpiar el contenedor antes de llenarlo

            data.forEach(departamento => {
                // Crear una tarjeta para cada departamento
                const card = document.createElement("div");
                card.classList.add("departamento-card");

                const nombre = document.createElement("h3");
                nombre.textContent = departamento.nombre;

                const descripcion = document.createElement("p");
                descripcion.textContent = departamento.descripcion;

                card.appendChild(nombre);
                card.appendChild(descripcion);

                departamentosContainer.appendChild(card);
            });
        })
        .catch(error => console.error("Error al cargar los departamentos:", error));
};
