package com.ubb.proyecto.controller;
import com.ubb.proyecto.model.Componentes;
import com.ubb.proyecto.service.ComponentesService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/componentes")
public class ComponentesController {
    @Autowired
    private ComponentesService componentesService;

    @GetMapping("/")
    public List<Componentes> getAllComponentes() {
        return componentesService.getAllComponentes();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Componentes> getComponentesById(@PathVariable Integer id) {
        Optional<Componentes> componentes = componentesService.getComponentesById(id);
        return componentes.map(ResponseEntity::ok) //si se encontró una categoría, crea una respuesta HTTP con codigo 200
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());//Si no, crea respuesta HTTP NOT FOUND
    }


    @PostMapping
    public ResponseEntity<Componentes> createComponentes(@RequestBody Componentes componentes) {
        Componentes saveComponentes = componentesService.saveComponentes(componentes);
        return ResponseEntity.status(HttpStatus.CREATED).body(saveComponentes);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Componentes> updateComponentes(@PathVariable Integer id, @RequestBody Componentes componentesDetails) {
        try {
            Componentes updateComponentes = componentesService.updateComponentes(id, componentesDetails);
            return ResponseEntity.ok(updateComponentes);
        } catch (RuntimeException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteComponentes(@PathVariable Integer id) {
        try {
            componentesService.deleteComponentes(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}

