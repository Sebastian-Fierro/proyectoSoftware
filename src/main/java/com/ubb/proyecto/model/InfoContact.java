package com.ubb.proyecto.model;


import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "info_contact")
public class InfoContact {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_contact", nullable = false)
    private Integer id_contact;

    @Column(name = "correo", nullable = false, length = 100)
    private String correo;

    @Column(name = "telefono", nullable = false)
    private Integer telefono;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_at", nullable = false)
    private Date updated_at;

    @ManyToOne
    @JoinColumn(name = "updated_by", referencedColumnName = "id_user")
    private Usuario updated_by;
    /*segun gpt esto es para identificar que usuario lo modifico, por eso es many y no one ya que muchos podrian actualizarlo y no
    exclusivamente uno, aunque me genera errores aunque cambie integers a usuario    lo de abajo es la version anterior*/ 
    
    /*@Column(name = "updated_at", nullable = false)
    private Integer updated_by;*/
    
    @Column(name = "instagram", nullable = false, length = 100)
    private String instagram;

    @Column(name = "facebook", nullable = false, length = 100)
    private String facebook;

    @OneToOne
    @JoinColumn(name = "id_user", referencedColumnName = "id_user")
    private Usuario usuario;

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

    public InfoContact(Integer id_contact, String correo, Integer telefono, Date updated_at, Usuario updated_by, String instagram, String facebook) {
        this.id_contact = id_contact;
        this.correo = correo;
        this.telefono = telefono;
        this.updated_at = updated_at;
        this.updated_by = updated_by; // Ahora acepta un objeto Usuario
        this.instagram = instagram;
        this.facebook = facebook;
    }
    

    // Getters y Setters
    public Integer getId_contact() {
        return id_contact;
    }

    public void setId_contact(Integer id_contact) {
        this.id_contact = id_contact;
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
