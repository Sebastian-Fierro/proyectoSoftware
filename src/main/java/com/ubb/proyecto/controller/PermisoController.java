package com.ubb.proyecto.controller;

import java.util.List;
import com.ubb.proyecto.model.Permiso;
import com.ubb.proyecto.service.PermisoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/permiso")
public class PermisoController {

    @Autowired
    private PermisoService permisoService;

     @GetMapping("")
    public List<Permiso> getAllPermisos() {
        return permisoService.getAllPermisos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Permiso> getPermisoById(@PathVariable Integer id) {
        Optional<Permiso> permiso = permisoService.getPermisoById(id);
        return permiso.map(ResponseEntity::ok) //si se encontró una categoría, crea una respuesta HTTP con codigo 200
                       .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());//Si no, crea respuesta HTTP NOT FOUND
    }

 @PostMapping
    public ResponseEntity<Permiso> createPermiso(@RequestBody Permiso permiso) {
        Permiso savedPermiso = permisoService.savePermiso(permiso);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedPermiso);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Permiso> updatePermiso(@PathVariable Integer id, @RequestBody Permiso permisoDetails) {
        try {
            Permiso updatePermiso = permisoService.updatePermiso(id, permisoDetails);
            return ResponseEntity.ok(updatePermiso);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePermiso(@PathVariable Integer id) {
        try {
            permisoService.deletePermiso(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

}
