package com.ubb.proyecto.controller;

import java.util.List;
import com.ubb.proyecto.model.Noticia;
<<<<<<< HEAD
=======
import com.ubb.proyecto.model.NoticiaDTO;
>>>>>>> master
import com.ubb.proyecto.repository.RepositorioNoticia;
import com.ubb.proyecto.service.NoticiaService;

import com.ubb.proyecto.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
@CrossOrigin
@RestController
@RequestMapping("/noticia")
public class NoticiaController {

    @Autowired
    private NoticiaService noticiaService;
    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private RepositorioNoticia repositorioNoticia;

    @GetMapping("/")
    public List<Noticia> getAllNoticias() {
        return noticiaService.getAllNoticias();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Noticia> getNoticiaById(@PathVariable Integer id) {
        Optional<Noticia> noticia = noticiaService.getNoticiaById(id);
        return noticia.map(ResponseEntity::ok) //si se encontró una categoría, crea una respuesta HTTP con codigo 200
                       .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());//Si no, crea respuesta HTTP NOT FOUND
    }

<<<<<<< HEAD
    @PostMapping("/crear")
    public ResponseEntity<String> crearNoticia(@RequestBody Noticia noticia, @RequestParam int usuarioId) {
=======

    @PostMapping("/crear")
    public ResponseEntity<?> crearNoticia(@RequestBody NoticiaDTO noticia, @RequestParam int usuarioId) {

>>>>>>> master
        if (!usuarioService.tienePermisoParaCrearNoticias(usuarioId)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("No tienes permiso para crear noticias.");
        }

        if (noticia.getTitulo() == null || noticia.getTitulo().isEmpty() ||
                noticia.getContenido() == null || noticia.getContenido().isEmpty()) {
            return ResponseEntity.badRequest().body("El título y el contenido no pueden estar vacíos.");
        }

<<<<<<< HEAD
        repositorioNoticia.save(noticia);
        return ResponseEntity.ok("Noticia creada con éxito.");
=======
        Noticia nuevaNoticia = noticiaService.crearNoticia(noticia, usuarioId);

        return ResponseEntity.status(HttpStatus.CREATED).body(nuevaNoticia);
>>>>>>> master
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarNoticia(@PathVariable int id, @RequestParam int usuarioId) {
        if (!usuarioService.tienePermisoParaBorrarNoticias(usuarioId)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("No tienes permiso para borrar noticias.");
        }

        Noticia noticiaExistente = repositorioNoticia.findById(id).orElse(null);
        if (noticiaExistente != null) {
            repositorioNoticia.delete(noticiaExistente);
            return ResponseEntity.ok("Noticia borrada con éxito.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Noticia no encontrada.");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> editarNoticia(@PathVariable int id, @RequestBody Noticia noticia, @RequestParam int usuarioId) {
        if (!usuarioService.tienePermisoParaEditarNoticias(usuarioId)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("No tienes permiso para editar noticias.");
        }

        Noticia noticiaExistente = repositorioNoticia.findById(id).orElse(null);
        if (noticiaExistente != null) {
            if (noticia.getTitulo() == null || noticia.getTitulo().isEmpty() ||
                    noticia.getContenido() == null || noticia.getContenido().isEmpty()) {
                return ResponseEntity.badRequest().body("El título y el contenido no pueden estar vacíos.");
            }

            noticiaExistente.setTitulo(noticia.getTitulo());
            noticiaExistente.setContenido(noticia.getContenido());
            repositorioNoticia.save(noticiaExistente);
            return ResponseEntity.ok("Noticia editada con éxito.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Noticia no encontrada.");
        }
    }
}

