package com.ubb.proyecto.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ubb.proyecto.model.Categoria;

@Repository
public interface RepositorioCategoria extends JpaRepository<Categoria, Integer> {
    
}
