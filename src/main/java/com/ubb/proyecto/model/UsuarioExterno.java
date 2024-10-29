package com.ubb.proyecto.model;

import java.sql.Date;

import jakarta.persistence.*;

@Entity
public class UsuarioExterno {

    @Id
    private String correo;

    private Integer nombre;

    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha_sub;

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
