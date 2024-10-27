package com.ubb.proyecto.repository;

import com.ubb.proyecto.model.Comentario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepositorioComentario extends JpaRepository<Comentario, Integer> {
}
