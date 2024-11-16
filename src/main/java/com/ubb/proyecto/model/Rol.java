package com.ubb.proyecto.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "rol")
public class Rol {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_rol", nullable = false)
    private Integer id_rol;

    @Column(name = "nombre", nullable = false, length = 30)
    private String nombre;

    @ManyToMany
    @JoinTable(name = "rolpermiso", joinColumns = @JoinColumn(name = "id_rol"),
            inverseJoinColumns = @JoinColumn(name = "id_permiso"))
    @JsonIgnore
    private List<Permiso> permisos;

    @OneToMany(mappedBy = "rol")
    @JsonIgnore
    private List<Usuario> usuarios;

    public Rol(Integer id_rol, String nombre) {
        this.id_rol = id_rol;
        this.nombre = nombre;
    }

    public Rol() {
    }

    // Getters y Setters
    public Integer getId_rol() {
        return id_rol;
    }

    public void setId_rol(Integer id_rol) {
        this.id_rol = id_rol;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Permiso> getPermisos() {
        return permisos;
    }

    public void setPermisos(List<Permiso> permisos) {
        this.permisos = permisos;
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }
}

