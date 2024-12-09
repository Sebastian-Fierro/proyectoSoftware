document.addEventListener("DOMContentLoaded", function () {
    const API_URL = "http://localhost:8080/infoContacto/3"; 

    fetch(API_URL)
        .then(response => {
            if (!response.ok) {
                throw new Error(`No se pudo cargar la información. Código de estado: ${response.status}`);
            }
            return response.json();
        })
        .then(data => {
            if (!data) throw new Error("La respuesta no contiene datos válidos.");

            //Rellena los datos obtenidos del backend
            document.getElementById("correo").textContent = data.correo || "No disponible";
            document.getElementById("telefono").textContent = `+ 56 ${data.telefono}` || "No disponible";
            document.getElementById("facebook").href = data.facebook || "#";
            document.getElementById("facebook").textContent = "Facebook";
            document.getElementById("instagram").href = data.instagram || "#";
            document.getElementById("instagram").textContent = "Instagram";
        })
        .catch(error => console.error("Error al cargar la información:", error));
});
