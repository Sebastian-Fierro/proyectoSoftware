document.addEventListener("DOMContentLoaded", function () {
    const API_URL = "http://localhost:8080/infoContacto/3"; // Ajusta el ID según sea necesario

    fetch(API_URL)
        .then(response => {
            if (!response.ok) throw new Error("No se pudo cargar la información.");
            return response.json();
        })
        .then(data => {
            // Rellenar los datos obtenidos del backend
            document.getElementById("correo").textContent = data.correo;
            document.getElementById("telefono").textContent = data.telefono;
            document.getElementById("facebook").href = data.facebook;
            document.getElementById("facebook").textContent = "Facebook";
            document.getElementById("instagram").href = data.instagram;
            document.getElementById("instagram").textContent = "Instagram";
        })
        .catch(error => console.error("Error al cargar la información:", error));
});
