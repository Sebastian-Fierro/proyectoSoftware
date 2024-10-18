package com.ubb.proyecto.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class UsuarioExterno {

    @Id
    private String correo;

    private Integer nombre;

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
}
