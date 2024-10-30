package com.ubb.proyecto.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.sql.Timestamp;
import java.util.List;

@Entity
@Table(name = "evento")
public class Evento {

    private Integer id;
    private String nombre;


    public Evento(Integer id, String nombre, String lugar, LocalTime horario) {
        this.id = id;
        this.nombre = nombre;
        this.lugar = lugar;
        this.horario = horario;
    }

    public Integer getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_evento")
    private int idEvento;

    @Column(name = "lugar", nullable = false, length = 100)
    private String lugar;

    @Column(name = "descripción", nullable = false, length = 1000)
    private String descripcion;

    @Column(name = "horario", nullable = false)
    private LocalTime horario;

    @Column(name = "created_at", nullable = false, updatable = false)
    private Timestamp createdAt;

    @Column(name = "created_by", nullable = false)
    private int createdBy;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    @ManyToMany(mappedBy = "eventoList")
    private List<Comentario> comentarios;

    @ManyToMany
    @JoinTable(name = "usuarioevento", joinColumns = @JoinColumn(name = "id_evento"),
            inverseJoinColumns = @JoinColumn(name = "id_user"))
    private List<Usuario> usuarios;

    @ManyToMany
    @JoinTable(name = "eventocategoria", joinColumns = @JoinColumn(name = "id_evento"),
            inverseJoinColumns = @JoinColumn(name = "id_categoria"))
    private List<Categoria> categorias;

    // Getters y setters
    public int getIdEvento() {
        return idEvento;
    }

    public void setIdEvento(int idEvento) {
        this.idEvento = idEvento;
    }

    public String getLugar() {
        return lugar;
    }

    public void setLugar(String lugar) {
        this.lugar = lugar;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public LocalTime getHorario() {
        return horario;
    }

    public void setHorario(LocalTime horario) {
        this.horario = horario;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public int getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(int createdBy) {
        this.createdBy = createdBy;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}
