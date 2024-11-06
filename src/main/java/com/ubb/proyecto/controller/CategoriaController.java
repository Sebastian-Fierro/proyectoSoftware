package com.ubb.proyecto.controller;

import com.ubb.proyecto.model.Categoria;
import com.ubb.proyecto.service.CategoriaService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;

@RestController
@CrossOrigin
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

    @PutMapping("/categorias/{id}")
    public ResponseEntity<Categoria> actualizaCategoria(@PathVariable Integer id, @RequestBody Categoria categoriaDetails) {
        // Verificar que la categoría existe antes de intentar actualizarla
        if (id == null || !servicio.existCategoriaById(id)) {
            return ResponseEntity.badRequest().build();
        }

        try {
            // Actualizar la categoría con los nuevos detalles
            Categoria categoriaActualizada = servicio.updateCategoria(id, categoriaDetails);
            return ResponseEntity.ok(categoriaActualizada);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build(); // En caso de que la categoría no exista
        }
    }

    @DeleteMapping("/categorias/{id}")
    public  ResponseEntity <Categoria> eliminarCategoria(@PathVariable Integer id) {
        if(id==null || !servicio.existCategoriaById(id)){
            return ResponseEntity.badRequest().build();
        }
        servicio.deleteCategoria(id);
        return  ResponseEntity.noContent().build();
    }

    
}
