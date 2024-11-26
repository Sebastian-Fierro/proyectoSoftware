package com.ubb.proyecto.model;

import jakarta.persistence.*;

import java.util.Date;
import com.fasterxml.jackson.annotation.*;

@Entity
@Table(name = "info_contact")
public class InfoContact {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_contact", nullable = false)
    private Integer idContact;

    @Column(name = "correo", nullable = false, length = 100)
    private String correo;

    @Column(name = "telefono", nullable = false)
    private Integer telefono;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_at", nullable = false)
    private Date updated_at;
    
    @Column(name = "instagram", nullable = false, length = 100)
    private String instagram;

    @Column(name = "facebook", nullable = false, length = 100)
    private String facebook;

    @OneToOne
    @JoinColumn(name = "updated_by", referencedColumnName = "id_user")
    //@JsonIgnore creo que generaba un error
    private Usuario updated_by;

    @PrePersist
    public void prePersist() {
        if (updated_at == null) {
            updated_at = new Date();  // Asigna la fecha y hora actuales
        }
    }

    @PreUpdate
    public void preUpdate() {
        updated_at = new Date();  // Actualiza la fecha y hora al momento de la actualización
    }

    public InfoContact() {}

    public InfoContact(Integer idContact, String correo, Integer telefono, Date updated_at, Usuario updated_by, String instagram, String facebook) {
        this.idContact = idContact;
        this.correo = correo;
        this.telefono = telefono;
        this.updated_at = updated_at;
        this.updated_by = updated_by; 
        this.instagram = instagram;
        this.facebook = facebook;
    }
    
    // Getters y Setters
    public Integer getIdContact() {
        return idContact;
    }

    public void setId_contact(Integer idContact) {
        this.idContact = idContact;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public Integer getTelefono() {
        return telefono;
    }

    public void setTelefono(Integer telefono) {
        this.telefono = telefono;
    }

    public Date getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(Date updated_at) {
        this.updated_at = updated_at;
    }

    public Usuario getUpdated_by() {
        return updated_by;
    }

    public void setUpdated_by(Usuario updated_by) {

        this.updated_by = updated_by;
    }

    public String getInstagram() {
        return instagram;
    }

    public void setInstagram(String instagram) {
        this.instagram = instagram;
    }

    public String getFacebook() {
        return facebook;
    }

    public void setFacebook(String facebook) {
        this.facebook = facebook;
    }
}
