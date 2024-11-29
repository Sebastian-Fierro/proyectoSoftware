package com.ubb.proyecto.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.*;
import java.util.List;


@Entity
@Table(name = "permisos")
public class Permiso {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_permiso", nullable = false)
    private Integer idPermiso;

    @Column(name = "nombre", nullable = false, length = 30)
    private String nombre;

    @Column(name = "descripcion", nullable = false, columnDefinition = "TEXT")
    private String descripcion;

    @ManyToMany(mappedBy = "permisos")
    @JsonIgnore
    private List<Rol> roles;

    public Permiso(Integer idPermiso, String nombre) {
        this.idPermiso = idPermiso;
        this.nombre = nombre;
    }

    public Permiso() {
    }
    // Getters y Setters

    public Integer getIdPermiso() {
        return idPermiso;
    }

    public void setIdPermiso(Integer idPermiso) {
        this.idPermiso = idPermiso;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public List<Rol> getRoles() {
        return roles;
    }

    public void setRoles(List<Rol> rols) {
        this.roles = rols;
    }
}

