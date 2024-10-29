package com.ubb.proyecto.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ubb.proyecto.model.Categoria;

public interface RepositorioCategoria extends JpaRepository<Categoria, Integer> {

}
