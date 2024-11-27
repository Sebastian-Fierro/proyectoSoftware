package com.ubb.proyecto.model;

import jakarta.persistence.*;
import java.util.Date;
import java.util.List;
import com.fasterxml.jackson.annotation.*;

@Entity
@Table(name = "usuario")
public class Usuario {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_user;

    private String nombre;

    private String contraseña;

    private String correo;

    @Temporal(TemporalType.TIMESTAMP)
    private Date created_at;

    @ManyToOne
    @JoinColumn(name = "rol_user")
    //@JsonManagedReference provoca errror 415 en infocontact
    private Rol rol;

    @ManyToMany
    @JoinTable(name = "usuariocomentario", joinColumns = @JoinColumn(name = "id_usuario"),
            inverseJoinColumns = @JoinColumn(name = "id_comentario"))
    private List<Comentario> comentarios;

    @ManyToMany
    @JoinTable(name = "usuariocomponentes", joinColumns = @JoinColumn(name = "id_user"),
            inverseJoinColumns = @JoinColumn(name = "id_comp"))
    private List<Componentes> componentes;

    @ManyToMany
    @JoinTable(name = "usuariomultimedia", joinColumns = @JoinColumn(name = "id_user"),
            inverseJoinColumns = @JoinColumn(name = "id_mult"))
    private List<Multimedia> multimedia;

    @ManyToMany(mappedBy = "usuarios")
    private List<Evento> eventos;

    @ManyToMany
    @JoinTable(name = "usuarionoticia", joinColumns = @JoinColumn(name = "id_user"),
            inverseJoinColumns = @JoinColumn(name = "id_noticia"))
    private List<Noticia> noticias;
    
    @OneToOne(mappedBy = "updated_by")
    private InfoContact infoContact;

    public Usuario() {}
    public Usuario(Integer id_user, String nombre, String contraseña, String correo) {
        this.id_user = id_user;
        this.nombre = nombre;
        this.contraseña = contraseña;
        this.correo = correo;
    }

    // Getters y Setters
    public Integer getId_user() {
        return id_user;
    }

    public void setId_user(Integer id_user) {
        this.id_user = id_user;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }
}
