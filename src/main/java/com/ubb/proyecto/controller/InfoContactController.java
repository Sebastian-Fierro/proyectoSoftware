package com.ubb.proyecto.controller;

import java.util.List;
import com.ubb.proyecto.model.InfoContact;
import com.ubb.proyecto.model.Usuario;
//import com.ubb.proyecto.repository.RepositorioInfoContact;
import com.ubb.proyecto.service.InfoContactService;

//import com.ubb.proyecto.service.UsuarioService;
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
    /*private UsuarioService usuarioService;
    @Autowired
    private RepositorioInfoContact repositorioInfoContact;*/

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

    @PostMapping("/insert/{id_user}")
    public ResponseEntity<InfoContact> createInfoContact(/*@PathVariable Integer id_user,*/@RequestBody InfoContact infoContact){
            /*Usuario usuario = usuarioService.findById(id_user)
        .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
    infoContact.setUsuario(usuario);*/
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
