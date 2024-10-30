package com.ubb.proyecto.service;

import java.util.List;
import java.util.Optional;

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
        InfoContact infoContact = infoContactRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Contacto no encontrado"));

        infoContact.setCorreo(InfoContactDetails.getCorreo());

        return infoContactRepository.save(infoContact);
    }

    public void deleteInfoContact(Integer id) {
        infoContactRepository.deleteById(id);
    }

}
