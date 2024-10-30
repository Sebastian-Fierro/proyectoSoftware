package com.ubb.proyecto.controller;

import java.util.List;
import com.ubb.proyecto.model.Noticia;
import com.ubb.proyecto.service.NoticiaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/noticia")
public class NoticiaController {

    @Autowired
    private NoticiaService noticiaService;

    @GetMapping("")
    public List<Noticia> getAllNoticias() {
        return noticiaService.getAllNoticias();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Noticia> getNoticiaById(@PathVariable Integer id) {
        Optional<Noticia> noticia = noticiaService.getNoticiaById(id);
        return noticia.map(ResponseEntity::ok) //si se encontró una categoría, crea una respuesta HTTP con codigo 200
                       .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());//Si no, crea respuesta HTTP NOT FOUND
    }

    @PostMapping
    public ResponseEntity<Noticia> createNoticia(@RequestBody Noticia noticia) {
        Noticia savedNoticia = noticiaService.saveNoticia(noticia);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedNoticia);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteNoticia(@PathVariable Integer id) {
        try {
            noticiaService.deleteNoticia(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}

