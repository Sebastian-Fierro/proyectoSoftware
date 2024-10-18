package com.ubb.proyecto.model;

import jakarta.persistence.*;
import java.util.Date;

@Entity
public class Suscripcion {

    @Id
    @ManyToOne
    @JoinColumn(name = "id_categoria")
    private Categoria categoria;

    @Id
    private String correo;

    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha_sub;

    // Getters y Setters
    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public Date getFecha_sub() {
        return fecha_sub;
    }

    public void setFecha_sub(Date fecha_sub) {
        this.fecha_sub = fecha_sub;
    }
}
