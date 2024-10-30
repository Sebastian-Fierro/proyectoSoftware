package com.ubb.proyecto.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ubb.proyecto.model.UsuarioExterno;
import com.ubb.proyecto.repository.RepositorioUsuarioExterno;

@Service
public class UsuarioExternoService {
    @Autowired
    private RepositorioUsuarioExterno usuarioExternoRepository;
//arreglado
    public List<UsuarioExterno> getAllUsuariosExternos(){
        return usuarioExternoRepository.findAll();
    }

     public Optional<UsuarioExterno> getUsuariosExternosById(Long id) {
        return usuarioExternoRepository.findById(id);
    }


    public UsuarioExterno saveUsuarioExterno(UsuarioExterno usuarioExterno) {
        return usuarioExternoRepository.save(usuarioExterno);
    }

    // Actualiza Nombre
    public UsuarioExterno updateUsuarioExterno(Long id, UsuarioExterno usuarioExternoDetails){
        UsuarioExterno usuarioExterno = usuarioExternoRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        usuarioExterno.setNombre(usuarioExternoDetails.getNombre());

        return usuarioExternoRepository.save(usuarioExterno);
    }

    public void deleteUsuarioExterno(Long Id){
        usuarioExternoRepository.deleteById(Id);
    }
    
    /*
    public Optional<UsuarioExterno> getUsuariosExternosById(Integer id) {
        return usuarioExternoRepository.findBy;
    }


    public UsuarioExterno saveUsuarioExterno(UsuarioExterno usuarioExterno) {
        return usuarioExternoRepository.save(usuarioExterno);
    }

    // Actualiza Nombre
    public UsuarioExterno updateUsuarioExterno(Integer id, UsuarioExterno usuarioExternoDetails){
        UsuarioExterno usuarioExterno = usuarioExternoRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        usuarioExterno.setNombre(usuarioExternoDetails.getNombre());

        return usuarioExternoRepository.save(usuarioExterno);
    }

    public void deleteUsuarioExterno(Integer Id){
        usuarioExternoRepository.deleteById(Id);
    }
*/

}
