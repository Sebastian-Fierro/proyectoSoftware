package com.ubb.proyecto.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "comentario")
public class Comentario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_comentario")
    private int idComentario;

    @Column(name = "descripción", nullable = false, length = 200)
    private String descripcion;

    @Column(name = "correo", nullable = false, length = 70)
    private String correo;

    @Column(name = "fecha", nullable = false)
    private LocalDate fecha;

    @ManyToOne //esta malo
    @JoinColumn(name = "id_evento", nullable = false)
    private Evento evento;

    @ManyToMany(mappedBy = "comentarios")
    private List<Usuario> usuarios;

    // Getters y setters
    public int getIdComentario() {
        return idComentario;
    }

    public void setIdComentario(int idComentario) {
        this.idComentario = idComentario;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public Evento getEvento() {
        return evento;
    }

    public void setEvento(Evento evento) {
        this.evento = evento;
    }
}

