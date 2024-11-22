package com.ubb.proyecto.service;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ubb.proyecto.model.InfoContact;
import com.ubb.proyecto.model.Usuario;
import com.ubb.proyecto.repository.RepositorioInfoContact;

@Service
public class InfoContactService {
    @Autowired
    private RepositorioInfoContact infoContactRepository;
    @Autowired
    private UsuarioService usuarioService; 

    public List<InfoContact> getAllInfoContacts(){
        return infoContactRepository.findAll();
    }

    /*public Optional<InfoContact> getInfoContactById(Integer id) {
        return infoContactRepository.findById(id);
    }*/
    public Optional<InfoContact> getSingleInfoContact() {
        return infoContactRepository.findFirstByOrderByIdContactAsc();
    }

    public InfoContact saveInfoContact(InfoContact infoContact/*, Integer id_user*/){
        /*Usuario usuario = usuarioService.getUsuariosById(id_user).orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        infoContact.setcambio(usuario);*/
        if (infoContact.getCorreo() == null || infoContact.getTelefono() == null) {
            throw new IllegalArgumentException("Los campos 'correo' y 'telefono' son obligatorios.");
        }
        return infoContactRepository.save(infoContact);
    }

    public InfoContact createInfoContact(InfoContact infoContact, Integer id_user) {
        Usuario usuario = usuarioService.getUsuariosById(id_user).orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        infoContact.setUpdated_by(usuario);
        return infoContactRepository.save(infoContact);
    }

   //Solo actualiza correo?
    /*public InfoContact updateInfoContact(Integer id, InfoContact InfoContactDetails) {
        InfoContact infoContact = infoContactRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Contacto no encontrado"));

        infoContact.setCorreo(InfoContactDetails.getCorreo());


        return infoContactRepository.save(infoContact);
    }*/

    public InfoContact updateInfoContact(Integer id, InfoContact newInfoContact) {
        InfoContact existingContact = infoContactRepository.findById(id).orElseThrow(() -> new RuntimeException("InfoContact no encontrado."));
        
        if (newInfoContact.getUpdated_by() != null) {
            Usuario updatedBy = usuarioService.getUsuariosById(newInfoContact.getUpdated_by().getId_user())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado."));
            existingContact.setUpdated_by(updatedBy);
        }
        existingContact.setCorreo(newInfoContact.getCorreo());
        existingContact.setTelefono(newInfoContact.getTelefono());
        existingContact.setInstagram(newInfoContact.getInstagram());
        existingContact.setFacebook(newInfoContact.getFacebook());
        //existingContact.setUpdated_by(newInfoContact.getUpdated_by()); comentado porque está lo de arriba
        return infoContactRepository.save(existingContact);
    }

    public void deleteInfoContact(Integer id) {
        if (!infoContactRepository.existsById(id)) {
            throw new RuntimeException("InfoContact no encontrado.");
        }
        infoContactRepository.deleteById(id);
    }

}
