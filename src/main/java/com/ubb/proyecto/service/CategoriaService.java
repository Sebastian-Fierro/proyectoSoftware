package com.ubb.proyecto.service;
import com.ubb.proyecto.model.*;
import com.ubb.proyecto.repository.RepositorioCategoria;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriaService {

    @Autowired
    private RepositorioCategoria repositorio;
 
    public List<Categoria> getAllCategorias() {
        return repositorio.findAll();
    }

    public boolean existCategoriaById(int id) {
        return repositorio.existsById(id);
    }

    public Optional <Categoria> getCategoriaById(Integer id) {
        Optional <Categoria> categoriaOptional = repositorio.findById(id);
        return categoriaOptional;
    }

    public Categoria saveCategoria(Categoria categoria) {
        return repositorio.save(categoria);
    }

    public Categoria updateCategoria(Integer id, Categoria categoriaDetails) {
        Optional<Categoria> categoriaOptional = repositorio.findById(id);
        if (categoriaOptional.isPresent()) {
            Categoria categoria = categoriaOptional.get();
            categoria.setNombre(categoriaDetails.getNombre());
            return repositorio.save(categoria);
        } else {
            throw new RuntimeException("Categoria no encontrada");
        }
    }

    public void deleteCategoria(Integer id) {
        Optional<Categoria> categoriaOptional = repositorio.findById(id);
        if (categoriaOptional.isPresent()) {
            repositorio.deleteById(id);
        } else {
            throw new RuntimeException("Categoria no encontrada");
        }
    }

    
}

