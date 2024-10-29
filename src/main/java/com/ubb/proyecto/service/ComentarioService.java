package com.ubb.proyecto.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ubb.proyecto.model.Comentario;
import com.ubb.proyecto.repository.RepositorioComentario;

import java.util.List;
import java.util.Optional;

@Service

public class ComentarioService {
    @Autowired
    private RepositorioComentario comentarioRepository;

    public List<Comentario> getAllComentarios() {
        return comentarioRepository.findAll();
    }

      public Optional<Comentario> getComentarioById(Integer id) {
        return comentarioRepository.findById(id);
    }

    public Comentario savComentario(Comentario comentario) {
        return comentarioRepository.save(comentario);
    }

    public void deleteComentario(Integer id) {
        comentarioRepository.deleteById(id);
    }
    
}
