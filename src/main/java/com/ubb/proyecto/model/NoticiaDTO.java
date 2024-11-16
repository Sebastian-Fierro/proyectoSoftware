package com.ubb.proyecto.model;

import java.time.LocalDateTime;
import java.util.List;

public class NoticiaDTO {

    private String titulo;
    private String contenido;

    public NoticiaDTO() {
    }

    public NoticiaDTO(String titulo, String contenidouariosIds) {
        this.titulo = titulo;
        this.contenido = contenido;

    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

}

