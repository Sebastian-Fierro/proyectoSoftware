package com.ubb.proyecto.model;

import jakarta.persistence.*;

@Entity
@Table(name = "rol")
public class Rol {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_rol", nullable = false)
    private Integer id_rol;

    @Column(name = "nombre", nullable = false, length = 30)
    private String nombre;

    // Getters y Setters

    public Integer getId_rol() {
        return id_rol;
    }

    public void setId_rol(Integer idRol) {
        this.id_rol = idRol;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}

