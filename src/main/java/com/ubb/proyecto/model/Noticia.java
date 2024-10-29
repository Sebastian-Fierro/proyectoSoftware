package com.ubb.proyecto.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "noticia")
public class Noticia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_noticia", nullable = false)
    private Integer idNoticia;

    @Column(name = "titulo", nullable = false, length = 100)
    private String titulo;

    @Column(name = "contenido", nullable = false, length = 1000)
    private String contenido;

    @Column(name = "created_at", nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime createdAt;

    @Column(name = "created_by", nullable = false)
    private Integer createdBy;

    @ManyToMany
    @JoinTable(name = "noticiacategoria", joinColumns = @JoinColumn(name = "id_noticia"),
            inverseJoinColumns = @JoinColumn(name = "id_categoria"))
    private List<Categoria> categoriaList;

    // Getters y Setters

    public Integer getIdNoticia() {
        return idNoticia;
    }

    public void setIdNoticia(Integer idNoticia) {
        this.idNoticia = idNoticia;
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

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public Integer getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Integer createdBy) {
        this.createdBy = createdBy;
    }
}
