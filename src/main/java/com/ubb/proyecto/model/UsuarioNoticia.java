package com.ubb.proyecto.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class UsuarioNoticia {

    @Id
    @ManyToOne
    @JoinColumn(name = "id_user")
    private Usuario usuario;

    @Id
    @ManyToOne
    @JoinColumn(name = "id_noticia")
    private Noticia noticia;

    // Getters y Setters
    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Noticia getNoticia() {
        return noticia;
    }

    public void setNoticia(Noticia noticia) {
        this.noticia = noticia;
    }
}
