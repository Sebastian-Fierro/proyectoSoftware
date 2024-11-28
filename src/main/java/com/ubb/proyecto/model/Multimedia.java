package com.ubb.proyecto.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "multimedia")
public class Multimedia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_mult")
    private int idMult;

    @Column(name = "nombre", nullable = false, length = 50)
    private String nombre;

    @Column(name = "tipo", nullable = false, length = 20)
    private String tipo;

    @Column(name = "url", nullable = false)
    private String url;
/*
    @ManyToMany(mappedBy = "multimedia")
    private List<Usuario> usuarios;
*/
    // Getters and Setters

    public int getIdMult() {
        return idMult;
    }

    public void setIdMult(int idMult) {
        this.idMult = idMult;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
