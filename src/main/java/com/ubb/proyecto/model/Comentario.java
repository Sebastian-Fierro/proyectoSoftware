package com.ubb.proyecto.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "comentario")
public class Comentario {
    
    private Integer id;
    private String texto;

    // Constructor
    public Comentario(Integer id, String texto) {
        this.id = id;
        this.texto = texto;
    }

    // Getters y Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }
    
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

    @ManyToMany(mappedBy = "comentarios")
    private List<Usuario> usuarios;

    @ManyToMany
    @JoinTable(name = "eventocomentario", joinColumns = @JoinColumn(name = "id_comentario"),
            inverseJoinColumns = @JoinColumn(name = "id_evento"))
    private List<Evento> eventoList;

    @ManyToOne
    @JoinColumn(name = "correo_externo")
    private UsuarioExterno usuarioExterno;

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

}

