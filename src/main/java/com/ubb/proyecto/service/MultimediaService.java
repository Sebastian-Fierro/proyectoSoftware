package com.ubb.proyecto.service;

import com.ubb.proyecto.model.Multimedia;
import com.ubb.proyecto.repository.RepositorioMultimedia;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MultimediaService {

    @Autowired
    private RepositorioMultimedia repositorioMultimedia;

    public List<Multimedia> getAllMultimedia() {
        return repositorioMultimedia.findAll();
    }

    public Multimedia addMultimedia(Multimedia multimedia) {
        // Asegúrate de que el tipo esté bien asignado
        if (multimedia.getTipo() == null || multimedia.getTipo().isEmpty()) {
            throw new RuntimeException("Tipo de multimedia es obligatorio");
        }
        return repositorioMultimedia.save(multimedia);
        
    }

    public Multimedia updateMultimedia(int id, Multimedia multimedia) {
        Optional<Multimedia> existingMultimedia = repositorioMultimedia.findById(id);
        if (existingMultimedia.isPresent()) {
            Multimedia updatedMultimedia = existingMultimedia.get();
            updatedMultimedia.setNombre(multimedia.getNombre());
            updatedMultimedia.setTipo(multimedia.getTipo());
            updatedMultimedia.setUrl(multimedia.getUrl());
            return repositorioMultimedia.save(updatedMultimedia);
        } else {
            throw new RuntimeException("Contenido multimedia no encontrado");
        }
    }

    public void deleteMultimedia(int id) {
        repositorioMultimedia.deleteById(id);
    }

    /*public boolean isValidURL(String url) {
        try {
            new URL(url).toURI();
            return true;
        } catch (Exception e) {
            return false;
        }
    }*/
    
}
