package com.ubb.proyecto.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ubb.proyecto.model.Noticia;
import com.ubb.proyecto.repository.RepositorioNoticia;


@Service
public class NoticiaService {
    @Autowired
    private RepositorioNoticia noticiaRepository;

    public List<Noticia> getAllNoticias() {
        return noticiaRepository.findAll();
    }

    public Optional<Noticia> getNoticiaById(Integer id) {
        return noticiaRepository.findById(id);
    }

    public Noticia saveNoticia(Noticia noticia) {
        return noticiaRepository.save(noticia);
    }

    public void updateNoticia(Integer id, Noticia noticiaDetails) {
        Noticia noticia = noticiaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Noticia no encontrada"));

        noticia.setNombre(noticiaDetails.getNombre());
    }
    public void deleteNoticia(Integer id) {
        noticiaRepository.deleteById(id);
    }

}
