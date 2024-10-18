package com.ubb.proyecto.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class UsuarioEvento {

    @Id
    @ManyToOne
    @JoinColumn(name = "id_user")
    private Usuario usuario;

    @Id
    @ManyToOne
    @JoinColumn(name = "id_evento")
    private Evento evento;

    // Getters y Setters
    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Evento getEvento() {
        return evento;
    }

    public void setEvento(Evento evento) {
        this.evento = evento;
    }
}