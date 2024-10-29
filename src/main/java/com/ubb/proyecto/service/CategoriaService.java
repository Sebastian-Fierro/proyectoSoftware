package com.ubb.proyecto.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ubb.proyecto.model.*;
import com.ubb.proyecto.repository.RepositorioCategoria;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriaService {

    @Autowired
    private RepositorioCategoria categoriaRepository;

    public List<Categoria> getAllCategorias() {
        return categoriaRepository.findAll();
    }

    public Optional<Categoria> getCategoriaById(Integer id) {
        return categoriaRepository.findById(id);
    }

    public Categoria saveCategoria(Categoria categoria) {
        return categoriaRepository.save(categoria);
    }

    public Categoria updateCategoria(Integer id, Categoria categoriaDetails) {
        Categoria categoria = categoriaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Categoria no encontrada"));

        categoria.setNombre(categoriaDetails.getNombre());

        return categoriaRepository.save(categoria);
    }

    public void deleteCategoria(Integer id) {
        categoriaRepository.deleteById(id);
    }
}

