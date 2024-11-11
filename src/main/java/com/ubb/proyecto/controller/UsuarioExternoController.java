package com.ubb.proyecto.controller;

import java.util.List;
import com.ubb.proyecto.model.UsuarioExterno;
import com.ubb.proyecto.service.UsuarioExternoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/usuarioExterno")
public class UsuarioExternoController {

    @Autowired
    private UsuarioExternoService usuarioExternoService;

    @GetMapping ("")
    public List <UsuarioExterno> getAllUsuariosExternos(){
        return usuarioExternoService.getAllUsuariosExternos();
    }
    
    
    @GetMapping("/{id}")
    public ResponseEntity<UsuarioExterno> getUsuarioExternoById(@PathVariable String id){
        Optional<UsuarioExterno> usuarioExterno = usuarioExternoService.getUsuariosExternosById(id);
        return usuarioExterno.map(ResponseEntity::ok)
                        .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PostMapping
    public ResponseEntity<UsuarioExterno> createUsuarioExterno(@RequestBody UsuarioExterno usuarioExterno){
        UsuarioExterno savedUsuarioExterno = usuarioExternoService.saveUsuarioExterno(usuarioExterno);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedUsuarioExterno);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UsuarioExterno> updateUsuarioExterno(@PathVariable String id, @RequestBody UsuarioExterno usuarioExternoDetails) {
        try {
            UsuarioExterno updatedUsuarioExterno = usuarioExternoService.updateUsuarioExterno((id), usuarioExternoDetails);
            return ResponseEntity.ok(updatedUsuarioExterno);
        } catch(RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUsuarioExterno(@PathVariable String id) {
        try {
            usuarioExternoService.deleteUsuarioExterno(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
