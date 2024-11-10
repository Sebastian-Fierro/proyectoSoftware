package com.ubb.proyecto.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ubb.proyecto.model.InfoContact;
import com.ubb.proyecto.service.InfoContactService;

import java.util.Optional;
//ingresar una opcion de contacto
//clave foranea tiene un int rol user
@RestController
@RequestMapping("/infoContacto")
public class InfoContactController {

    @Autowired
    private InfoContactService infoContactService;

    @GetMapping("")
    public List<InfoContact> getAllInfoContacts(){
        return infoContactService.getAllInfoContacts();
    }

    @GetMapping("/{id}")
    public ResponseEntity<InfoContact> getInfoContactById(@PathVariable Integer id){
        try{
        Optional<InfoContact> infoContacto = infoContactService.getInfoContactById(id);
        return infoContacto.map(ResponseEntity::ok)
                        .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
        }catch(Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping
    public ResponseEntity<?> createInfoContact(@RequestBody InfoContact infoContact){
                //para validar que exista el usuario, no creo que sea necesario 
        /*if (infoContact.getUsuario() == null || infoContact.getUsuario().getId_user() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }*/
        try{ 
        InfoContact savedInfoContact = infoContactService.saveInfoContact((infoContact));
        return ResponseEntity.status(HttpStatus.CREATED).body(savedInfoContact);
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al guardar InfoContact: " + e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<InfoContact> updateInfoContact(@PathVariable Integer id, @RequestBody InfoContact infoContactDetails){
        try {
            InfoContact updateInfoContact = infoContactService.updateInfoContact(id, infoContactDetails);
            return ResponseEntity.ok(updateInfoContact);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
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
