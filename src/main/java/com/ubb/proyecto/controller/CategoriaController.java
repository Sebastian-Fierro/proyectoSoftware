package com.ubb.proyecto.controller;

import com.ubb.proyecto.model.Categoria;
import com.ubb.proyecto.service.CategoriaService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;

@Controller
public class CategoriaController {

    @Autowired
    private CategoriaService servicio;

    @GetMapping("/categorias")
    public List<Categoria> listarCategorias() {
       return servicio.getAllCategorias();
    }

    @GetMapping("/categorias/{id}")
    public  ResponseEntity <Categoria> getCategoria(@PathVariable int id) {
        Optional <Categoria> opt =  servicio.getCategoriaById(id);

        if(opt.isEmpty()){
            return ResponseEntity.notFound().build();
        }else{
            return ResponseEntity.ok(opt.get());
        }
    }

    @PostMapping("/categorias")
    public  ResponseEntity <Categoria> crearCategoria(@RequestBody Categoria categoria) {
        if(categoria.getIdCategory()!=null){
            return ResponseEntity.badRequest().build();
        }
        servicio.saveCategoria(categoria);
        return ResponseEntity.ok(categoria);
    }



    
}
