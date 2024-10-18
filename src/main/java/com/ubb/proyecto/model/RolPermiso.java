package com.ubb.proyecto.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class RolPermiso {

    @Id
    @ManyToOne
    @JoinColumn(name = "id_rol")
    private Rol rol;

    @Id
    @ManyToOne
    @JoinColumn(name = "id_permiso")
    private Permiso permiso;

    // Getters y Setters
    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    public Permiso getPermiso() {
        return permiso;
    }

    public void setPermiso(Permiso permiso) {
        this.permiso = permiso;
    }
}
