package com.ubb.proyecto.controller;

import java.util.List;

import com.ubb.proyecto.model.Rol;
import com.ubb.proyecto.service.RolService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/rol")

public class RolController {
    @Autowired
    private RolService rolService;

    @GetMapping("")
    public List<Rol> getAllRols() {
        return rolService.getAllRol();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Rol> getRolById(@PathVariable Integer id) {
        Optional<Rol> rol = rolService.getRolById(id);
        return rol.map(ResponseEntity::ok) //si se encontró una categoría, crea una respuesta HTTP con codigo 200
                       .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());//Si no, crea respuesta HTTP NOT FOUND
    }


    @PostMapping
    public ResponseEntity<Rol> createRol(@RequestBody Rol rol) {
        Rol savedRol = rolService.saveRol(rol);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedRol);
    }

     @PutMapping("/{id}")
    public ResponseEntity<Rol> updateRol(@PathVariable Integer id, @RequestBody Rol rolDetails) {
        try {
            Rol updatedRol = rolService.updateRol(id, rolDetails);
            return ResponseEntity.ok(updatedRol);
        } catch (RuntimeException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRol(@PathVariable Integer id) {
        try {
            rolService.deleteRol(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
