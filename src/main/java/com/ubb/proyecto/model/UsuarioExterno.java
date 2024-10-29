package com.ubb.proyecto.model;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.*;

@Entity
public class UsuarioExterno {

    @Id
    private String correo;

    private Integer nombre;

    private LocalDateTime fecha;

    @ManyToMany
    @JoinTable(name = "externo_categoria", joinColumns = @JoinColumn(name = "correo_externo"),
            inverseJoinColumns = @JoinColumn(name = "id_categoria"))
    private List<Categoria> categoriasExt;

    @OneToMany(mappedBy = "correo_externo")
    private List<Comentario> comentarios;

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

}
