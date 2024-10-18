package com.ubb.proyecto.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class UsuarioComentario {

    @Id
    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;

    @Id
    @ManyToOne
    @JoinColumn(name = "id_comentario")
    private Comentario comentario;

    // Getters y Setters
    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Comentario getComentario() {
        return comentario;
    }

    public void setComentario(Comentario comentario) {
        this.comentario = comentario;
    }
}
