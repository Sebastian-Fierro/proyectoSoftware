package com.ubb.proyecto.repository;

import com.ubb.proyecto.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepositorioUsuario extends JpaRepository<Usuario, Integer> {
    Usuario findByCorreoAndContraseña(String correo, String contraseña); // Nuevo método
}
