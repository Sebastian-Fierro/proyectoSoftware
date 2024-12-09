package com.ubb.proyecto.controller;

import com.ubb.proyecto.model.Multimedia;
import com.ubb.proyecto.service.MultimediaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/multimedia")
public class MultimediaController {

    @Autowired
    private MultimediaService multimediaService;

    // GET: obtener todos los contenidos multimedia
    @GetMapping
    public List<Multimedia> getAllMultimedia() {
        return multimediaService.getAllMultimedia();
    }

    // POST: agregar un nuevo contenido multimedia (audio, video, etc.)
    @PostMapping("/agregar/id")
    public ResponseEntity<Multimedia> addMultimedia(@RequestBody Multimedia multimedia) {
        Multimedia createdMultimedia = multimediaService.addMultimedia(multimedia);
        return ResponseEntity.status(201).body(createdMultimedia);
    }

    // PUT: editar un contenido multimedia (audio, video, etc.)
    @PutMapping("/editar/{id}")
    public ResponseEntity<Multimedia> updateMultimedia(@PathVariable int id, @RequestBody Multimedia multimedia) {
        Multimedia updatedMultimedia = multimediaService.updateMultimedia(id, multimedia);
        return ResponseEntity.ok(updatedMultimedia);
    }

    // DELETE: eliminar un contenido multimedia
    @DeleteMapping("/borrar{id}")
    public ResponseEntity<Void> deleteMultimedia(@PathVariable int id) {
        multimediaService.deleteMultimedia(id);
        return ResponseEntity.noContent().build();
    }
}
