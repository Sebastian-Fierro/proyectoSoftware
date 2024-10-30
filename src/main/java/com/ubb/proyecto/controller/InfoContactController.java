package com.ubb.proyecto.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ubb.proyecto.model.InfoContact;
import com.ubb.proyecto.service.InfoContactService;

import java.util.Optional;

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
        Optional<InfoContact> infoContacto = infoContactService.getInfoContactById(id);
        return infoContacto.map(ResponseEntity::ok)
                        .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PostMapping
    public ResponseEntity<InfoContact> createInfoContact(@RequestBody InfoContact infoContact){
        InfoContact savedInfoContact = infoContactService.saveInfoContact((infoContact));
        return ResponseEntity.status(HttpStatus.CREATED).body(savedInfoContact);
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
