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

    public Optional<InfoContact> getInfoContactById(Integer id) {
        return infoContactRepository.findById(id);
    }

    public InfoContact saveInfoContact(InfoContact infoContact/*, Integer id_user*/){
        /*Usuario usuario = usuarioService.getUsuariosById(id_user).orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        infoContact.setcambio(usuario);*/
        return infoContactRepository.save(infoContact);
    }

    /*public InfoContact createInfoContact(InfoContact infoContact, Integer id_user) {
        // Obtiene el usuario por ID
        Usuario usuario = usuarioService.getUsuariosById(id_user)
            .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        // Asigna el usuario al objeto InfoContact
        infoContact.setUsuario(usuario);

        // Guarda el objeto InfoContact en la base de datos
        return infoContactRepository.save(infoContact);
    }*/

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
