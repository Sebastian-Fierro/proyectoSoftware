package com.ubb.proyecto.repository;

import com.ubb.proyecto.model.Noticia;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepositorioNoticia extends JpaRepository<Noticia, Integer> {
}
