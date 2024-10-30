package com.ubb.proyecto.model;

import jakarta.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
public class Usuario {

    private Integer id;

    // Constructor
    public Usuario(Integer id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    // Getters y Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_user;

    private String nombre;

    private String contraseña;

    private String correo;

    @Temporal(TemporalType.TIMESTAMP)
    private Date created_at;

    @ManyToOne
    @JoinColumn(name = "id_rol")
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
