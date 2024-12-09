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

@CrossOrigin
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
    public ResponseEntity<InfoContact>getSingleInfoContact(@PathVariable Integer id){
        Optional<InfoContact> infoContacto = infoContactService.getInfoContactById(id);
        return infoContacto.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PostMapping("/insert/{id_user}")
    public ResponseEntity<?> createInfoContact(@PathVariable Integer id_user, @RequestBody InfoContact infoContact){

        Optional<InfoContact> existingContact = infoContactService.getInfoContactById(infoContact.getIdContact());
        if (existingContact.isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Ya existe un registro de InfoContact. Solo puedes actualizarlo.");
        }
        
        Usuario usuario = usuarioService.getUsuariosById(id_user).orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        infoContact.setUpdated_by(usuario);

        InfoContact savedContact = infoContactService.saveInfoContact(infoContact);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedContact);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateInfoContact(@PathVariable Integer id, @RequestBody InfoContact infoContactDetails) {
        try {
            //Busca contacto existente
            Optional<InfoContact> existingContactOpt = infoContactService.getInfoContactById(id);
            if (existingContactOpt.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No existe un registro de InfoContact para actualizar.");
            }

            //Actualizar los campos
            InfoContact existingContact = existingContactOpt.get();
            existingContact.setCorreo(infoContactDetails.getCorreo());
            existingContact.setTelefono(infoContactDetails.getTelefono());
            existingContact.setInstagram(infoContactDetails.getInstagram());
            existingContact.setFacebook(infoContactDetails.getFacebook());

            //Guardar los cambios
            InfoContact updatedContact = infoContactService.saveInfoContact(existingContact);

            //Devolver respuesta exitosa
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
