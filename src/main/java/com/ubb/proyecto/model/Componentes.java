package com.ubb.proyecto.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "componentes")
public class Componentes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_comp")
    private int idComp;

    @Column(name = "nombre", nullable = false, length = 30)
    private String nombre;

    @Column(name = "descripción", nullable = false)
    private String descripcion;

    @ManyToMany(mappedBy = "componentes")
    private List<Usuario> usuarios;

    // Getters y Setters

    public int getIdComp() {
        return idComp;
    }

    public void setIdComp(int idComp) {
        this.idComp = idComp;
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
}
