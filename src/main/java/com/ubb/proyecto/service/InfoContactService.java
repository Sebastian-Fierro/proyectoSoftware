package com.ubb.proyecto.service;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ubb.proyecto.model.InfoContact;
import com.ubb.proyecto.repository.RepositorioInfoContact;

@Service

public class InfoContactService {

    @Autowired
    private RepositorioInfoContact infoContactRepository;

    public List<InfoContact> getAllInfoContacts(){
        return infoContactRepository.findAll();
    }

    public Optional<InfoContact> getInfoContactById(Integer id) {
        return infoContactRepository.findById(id);
    }

    public InfoContact saveInfoContact(InfoContact infoContact) {
        return infoContactRepository.save(infoContact);
    }

   //Solo actualiza correo?
    public InfoContact updateInfoContact(Integer id, InfoContact InfoContactDetails) {
        InfoContact infoContact = infoContactRepository.findById(id).orElseThrow(
            () -> new RuntimeException("Contacto no encontrado"));
            //infoContact.setUpdated_at(new Date());  // Asignar la fecha actual

        infoContact.setCorreo(InfoContactDetails.getCorreo());
        /*infoContact.setTelefono(InfoContactDetails.getTelefono());
        infoContact.setUpdated_at(InfoContactDetails.getUpdated_at());
        infoContact.setUpdated_by(InfoContactDetails.getUpdated_by());
        infoContact.setInstagram(InfoContactDetails.getInstagram());
        infoContact.setFacebook(InfoContactDetails.getFacebook());*/

        return infoContactRepository.save(infoContact);
    }

    public void deleteInfoContact(Integer id) {
        infoContactRepository.deleteById(id);
    }

}
