package com.ubb.proyecto.service;

import com.ubb.proyecto.model.Multimedia;
import com.ubb.proyecto.repository.RepositorioMultimedia;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MultimediaService {

    @Autowired
    private RepositorioMultimedia repositorioMultimedia;

    public List<Multimedia> getAllPodcasts() {
        return repositorioMultimedia.findAll();
    }

    public Multimedia addPodcast(Multimedia multimedia) {
        return repositorioMultimedia.save(multimedia);
    }
}
