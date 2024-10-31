package com.ubb.proyecto.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class hola {

    @GetMapping("/hola")
    public String home() {
        return "hola"; // Nombre del archivo HTML sin la extensión
    }
}