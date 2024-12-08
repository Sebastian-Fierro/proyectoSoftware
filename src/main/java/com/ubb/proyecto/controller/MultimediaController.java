package com.ubb.proyecto.controller;

import com.ubb.proyecto.model.Multimedia;
import com.ubb.proyecto.service.MultimediaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/podcasts")
public class MultimediaController {

    @Autowired
    private MultimediaService multimediaService;

    @GetMapping
    public List<Multimedia> getAllPodcasts() {
        return multimediaService.getAllPodcasts();
    }

    @PostMapping
    public Multimedia addPodcast(@RequestBody Multimedia multimedia) {
        return multimediaService.addPodcast(multimedia);
    }
}

