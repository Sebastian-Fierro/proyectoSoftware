document.addEventListener("DOMContentLoaded", function () {
    // Simulación de datos obtenidos del backend
    const contactData = {
        correo: "contacto@pagina.com",
        telefono: "+123 456 789",
        facebook: "https://facebook.com/paginaOficial",
        instagram: "https://instagram.com/paginaOficial"
    };

    // Rellenar los datos en la página
    document.getElementById("correo").textContent = contactData.correo;
    document.getElementById("telefono").textContent = contactData.telefono;
    document.getElementById("facebook").href = contactData.facebook;
    document.getElementById("facebook").textContent = "Facebook";
    document.getElementById("instagram").href = contactData.instagram;
    document.getElementById("instagram").textContent = "Instagram";
});
