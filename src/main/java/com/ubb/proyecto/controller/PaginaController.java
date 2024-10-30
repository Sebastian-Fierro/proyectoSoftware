package com.ubb.proyecto.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class PaginaController {

    @GetMapping("/")
    public String inicio() {
        return "inicio";
    }

    @GetMapping("/contacto")
    public String contacto() {
        return "contacto";
    }
}
