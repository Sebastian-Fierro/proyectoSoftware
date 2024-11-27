package com.ubb.proyecto.controller;

import java.util.List;
import com.ubb.proyecto.model.InfoContact;
import com.ubb.proyecto.model.Usuario;
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

    @GetMapping("")
    public List<InfoContact> getAllInfoContacts(){
        return infoContactService.getAllInfoContacts();
    }

    @GetMapping("/{id}")
    public ResponseEntity<InfoContact> /*getInfoContactById*/getSingleInfoContact(@PathVariable Integer id){
        Optional<InfoContact> infoContacto = infoContactService.getInfoContactById(id)/*getSingleInfoContact()*/;
        return infoContacto.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PostMapping("/insert/{id_user}")
    public ResponseEntity</*InfoContact*/?> createInfoContact(@PathVariable Integer id_user, @RequestBody InfoContact infoContact){
        //InfoContact createdInfoContact = infoContactService.createInfoContact(infoContact); definir createInfoContact en service pa k funcione
        //return new ResponseEntity<>(createdInfoContact, HttpStatus.CREATED);
        
        //Optional<InfoContact> existingContact = infoContactService.getSingleInfoContact();
        Optional<InfoContact> existingContact = infoContactService.getInfoContactById(infoContact.getIdContact());
        if (existingContact.isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Ya existe un registro de InfoContact. Solo puedes actualizarlo.");
        }
        
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
    public ResponseEntity<?> updateInfoContact(@PathVariable Integer id, @RequestBody InfoContact infoContactDetails) {
    try {
        if (infoContactDetails.getUpdated_by() == null || infoContactDetails.getUpdated_by().getId_user() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("El campo 'updated_by' o 'id_user' está ausente.");
        }

        Optional<InfoContact> existingContactOpt = infoContactService.getInfoContactById(id);
        if (existingContactOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No existe un registro de InfoContact para actualizar.");
        }

        InfoContact existingContact = existingContactOpt.get();

        Usuario updatedBy = usuarioService.getUsuariosById(infoContactDetails.getUpdated_by().getId_user())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado."));

        existingContact.setCorreo(infoContactDetails.getCorreo());
        existingContact.setTelefono(infoContactDetails.getTelefono());
        existingContact.setInstagram(infoContactDetails.getInstagram());
        existingContact.setFacebook(infoContactDetails.getFacebook());
        existingContact.setUpdated_by(updatedBy);

        InfoContact updatedContact = infoContactService.saveInfoContact(existingContact);

        return ResponseEntity.ok(updatedContact);

    } catch (IllegalArgumentException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    } catch (RuntimeException e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error interno del servidor: " + e.getMessage());
    }
}

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteInfoContact(@PathVariable Integer id) {
        try {
            infoContactService.deleteInfoContact((id));
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

}
