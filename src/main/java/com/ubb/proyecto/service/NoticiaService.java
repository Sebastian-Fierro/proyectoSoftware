package com.ubb.proyecto.service;

import java.util.List;
import java.util.Optional;

import com.ubb.proyecto.model.NoticiaDTO;
import com.ubb.proyecto.model.Usuario;
import com.ubb.proyecto.repository.RepositorioUsuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ubb.proyecto.model.Noticia;
import com.ubb.proyecto.repository.RepositorioNoticia;


@Service
public class NoticiaService {
    @Autowired
    private RepositorioNoticia noticiaRepository;
    @Autowired
    private RepositorioUsuario repositorioUsuario;

    public List<Noticia> getAllNoticias() {
        return noticiaRepository.findAll();
    }

    public Optional<Noticia> getNoticiaById(Integer id) {
        return noticiaRepository.findById(id);
    }

    public Noticia saveNoticia(Noticia noticia) {
        return noticiaRepository.save(noticia);
    }
    public Noticia crearNoticia(NoticiaDTO noticiaDTO, int usuarioId) {
        // Buscar el usuario por su ID
        Usuario usuario = repositorioUsuario.findById(usuarioId)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        // Crear la nueva noticia

        Noticia noticia = new Noticia();
        noticia.setTitulo(noticiaDTO.getTitulo());
        noticia.setContenido(noticiaDTO.getContenido());

        // Asociar el usuario a la noticia
        noticia.getUsuarios().add(usuario);

        // Guardar la noticia en la base de datos
        return noticiaRepository.save(noticia);
    }

    public Noticia updateNoticia(Integer id, Noticia noticiaDetails) {
        Noticia noticia = noticiaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Noticia no encontrada"));

        noticia.setTitulo(noticiaDetails.getTitulo());
        noticia.setContenido(noticiaDetails.getContenido());

        return noticiaRepository.save(noticia);
    }

    public void deleteNoticia(Integer id) {
        noticiaRepository.deleteById(id);
    }

}
