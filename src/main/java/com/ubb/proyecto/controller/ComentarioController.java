package com.ubb.proyecto.controller;

import java.util.List;
import com.ubb.proyecto.model.Comentario;
import com.ubb.proyecto.service.ComentarioService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@RestController
@RequestMapping("/comentario")
public class ComentarioController {

    @Autowired
   private ComentarioService comentarioService;

    @GetMapping("")
    public List<Comentario> list(){
        return comentarioService.getAllComentarios();
    }

    @GetMapping("{id}")
    public ResponseEntity<Comentario> getComentarioById(@PathVariable Integer id){
        Optional<Comentario> comentario = comentarioService.getComentarioById(id);
        return comentario.map(ResponseEntity::ok)
                    .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());

    }

    @PostMapping
    public ResponseEntity<Comentario> createComentario(@RequestBody Comentario comentario){
        Comentario savedComentario = comentarioService.savComentario(comentario);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedComentario);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteComentario(@PathVariable Integer id) {
try {
    comentarioService.deleteComentario(id);
    return ResponseEntity.noContent().build();
} catch (RuntimeException e) {
    return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
}
    }
}
