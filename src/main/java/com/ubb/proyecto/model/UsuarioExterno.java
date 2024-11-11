package com.ubb.proyecto.model;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.*;

@Entity
public class UsuarioExterno {
    
    @Id
    @Column(name = "correo", nullable = false, length = 70)
    private String correo;

    @Column(name = "nombre", nullable = false, length = 100)
    private String nombre;

    // Constructor vacío
    public UsuarioExterno() {
    }

    // Constructor con parámetros
    public UsuarioExterno(String correo, String nombre) {
        this.correo = correo;
        this.nombre = nombre;
    }

    private LocalDateTime fechaSub;

    @ManyToMany
    @JoinTable(name = "externo_categoria", joinColumns = @JoinColumn(name = "correo_externo"),
            inverseJoinColumns = @JoinColumn(name = "id_categoria"))
    private List<Categoria> categoriasExt;

    @OneToMany(mappedBy = "correo") //revision
    private List<Comentario> comentarios;

    
    // Getters y Setters
    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public  LocalDateTime getFechaSub() {
        return fechaSub;
    }

    public void setFechaSub(LocalDateTime fecha) {
        this.fechaSub = fecha;
    }

    public List<Categoria> getCategoriasExt() {
        return categoriasExt;
    }

    public void setCategoriasExt(List<Categoria> categoriasExt) {
        this.categoriasExt = categoriasExt;
    }

    public List<Comentario> getComentarios() {
        return comentarios;
    }

    public void setComentarios(List<Comentario> comentarios) {
        this.comentarios = comentarios;
    }
}
