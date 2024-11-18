package com.ubb.proyecto.controller;

import java.util.List;
import com.ubb.proyecto.model.InfoContact;
import com.ubb.proyecto.model.Usuario;
import com.ubb.proyecto.repository.RepositorioInfoContact;
import com.ubb.proyecto.service.InfoContactService;

import com.ubb.proyecto.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/infoContacto")
public class InfoContactController {

    @Autowired
    private InfoContactService infoContactService;
    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private RepositorioInfoContact repositorioInfoContact;

    @GetMapping("")
    public List<InfoContact> getAllInfoContacts(){
        return infoContactService.getAllInfoContacts();
    }

    @GetMapping("/{id}")
    public ResponseEntity<InfoContact> /*getInfoContactById*/getSingleInfoContact(@PathVariable Integer id){
        Optional<InfoContact> infoContacto = infoContactService./*getInfoContactById(id)*/getSingleInfoContact();
        return infoContacto.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PostMapping("/insert/{id_user}")
    public ResponseEntity</*InfoContact*/?> createInfoContact(@PathVariable Integer id_user, @RequestBody InfoContact infoContact){
        //InfoContact createdInfoContact = infoContactService.createInfoContact(infoContact); definir createInfoContact en service pa k funcione
        //return new ResponseEntity<>(createdInfoContact, HttpStatus.CREATED);
        
        Optional<InfoContact> existingContact = infoContactService.getSingleInfoContact();

        if (existingContact.isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Ya existe un registro de InfoContact. Solo puedes actualizarlo.");
        }
        Usuario usuario = usuarioService.getUsuariosById(id_user).orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        infoContact.setUpdated_by(usuario);

        InfoContact savedContact = infoContactService.saveInfoContact(infoContact);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedContact);
        
        /*InfoContact savedInfoContact = infoContactService.saveInfoContact((infoContact));
        return ResponseEntity.status(HttpStatus.CREATED).body(savedInfoContact);*/
    }

    @PutMapping("/update/{id}")
    public ResponseEntity</*InfoContact*/?> updateInfoContact(@PathVariable Integer id, @RequestBody InfoContact infoContactDetails){

        if (infoContactDetails.getUpdated_by() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("El campo 'updated_by' está ausente.");
        }
        
        if (infoContactDetails.getUpdated_by().getId_user() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("El campo 'id_user' dentro de 'updated_by' está ausente.");
        }

        Optional<InfoContact> existingContact = infoContactService.getSingleInfoContact();
        if (existingContact.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No existe un registro de InfoContact para actualizar.");
        }
        
        Usuario updatedBy = usuarioService.getUsuariosById(infoContactDetails.getUpdated_by().getId_user()).orElseThrow(() -> new RuntimeException("Usuario no encontrado."));

        infoContactDetails.setUpdated_by(updatedBy);

        InfoContact updatedContact = infoContactService.updateInfoContact(existingContact.get().getIdContact(), infoContactDetails);
    
        return ResponseEntity.ok(updatedContact);
        
        //InfoContact infoContact = infoContactService.updateInfoContact(id, updatedInfoContact); definir updateInfoContact en service pa k funcione(?
        //return ResponseEntity.ok(infoContact);
        
        /*try {
            InfoContact updateInfoContact = infoContactService.updateInfoContact(id, infoContactDetails);
            return ResponseEntity.ok(updateInfoContact);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }*/
    }

    @DeleteMapping("/id")
    public ResponseEntity<Void> deleteInfoContact(@PathVariable Integer id) {
        try {
            infoContactService.deleteInfoContact((id));
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

}
