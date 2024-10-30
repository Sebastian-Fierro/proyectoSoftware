package com.ubb.proyecto.model;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.*;

@Entity
public class UsuarioExterno {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    private String correo;

    private Integer nombre;

    private LocalDateTime fecha;

    @ManyToMany
    @JoinTable(name = "externo_categoria", joinColumns = @JoinColumn(name = "correo_externo"),
            inverseJoinColumns = @JoinColumn(name = "id_categoria"))
    private List<Categoria> categoriasExt;

    @OneToMany(mappedBy = "correo") //revision
    private List<Comentario> comentarios;

    public Long getId() {
        return Id;
    }

    // Getters y Setters
    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public Integer getNombre() {
        return nombre;
    }

    public void setNombre(Integer nombre) {
        this.nombre = nombre;
    }

    public  LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
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
