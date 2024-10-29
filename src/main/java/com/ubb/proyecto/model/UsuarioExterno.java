package com.ubb.proyecto.model;

import java.time.LocalDateTime;

import jakarta.persistence.*;

@Entity
public class UsuarioExterno {

    @Id
    private String correo;

    private Integer nombre;

    private LocalDateTime fecha;

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
