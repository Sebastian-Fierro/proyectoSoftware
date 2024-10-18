package com.ubb.proyecto.model;

import jakarta.persistence.*;

@Entity
public class NoticiaCategoria {

    @Id
    @ManyToOne
    @JoinColumn(name = "id_noticia")
    private Noticia noticia;

    @Id
    @ManyToOne
    @JoinColumn(name = "id_categoria")
    private Categoria categoria;

    // Getters y Setters
    public Noticia getNoticia() {
        return noticia;
    }

    public void setNoticia(Noticia noticia) {
        this.noticia = noticia;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }
}

