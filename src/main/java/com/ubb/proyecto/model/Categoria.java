package com.ubb.proyecto.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "categoria")
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_category")
    private Integer idCategory;

    @Column(name = "nombre", nullable = false, length = 30)
    private String nombre;

    @ManyToMany(mappedBy = "categoriaList")
    private List<Noticia> noticiaList;

    @ManyToMany(mappedBy = "categorias")
    private List<Evento> eventos;

    @ManyToMany(mappedBy = "categoriasExt")
    private List<UsuarioExterno> usuarioExternos;

    // Getters y setters
    public Integer getIdCategory() {
        return idCategory;
    }

    public void setIdCategory(int idCategory) {
        this.idCategory = idCategory;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
