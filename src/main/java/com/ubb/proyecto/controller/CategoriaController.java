package com.ubb.proyecto.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ubb.proyecto.model.Categoria;
import com.ubb.proyecto.service.CategoriaService;

@RestController
@RequestMapping("/categoria")

public class CategoriaController {
    @Autowired
    CategoriaService categoriaService;

    @GetMapping("")
    public List<Categoria> list(){
        return categoriaService.getAllCategorias();
    }

    
}
