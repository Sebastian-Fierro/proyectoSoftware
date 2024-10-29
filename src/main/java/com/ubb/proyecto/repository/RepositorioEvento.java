package com.ubb.proyecto.repository;

import com.ubb.proyecto.model.Evento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepositorioEvento extends JpaRepository<Evento, Integer> {
}
