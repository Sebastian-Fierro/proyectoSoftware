package com.ubb.proyecto.repository;

import com.ubb.proyecto.model.UsuarioExterno;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepositorioUsuarioExterno extends JpaRepository<UsuarioExterno, String> {
}
