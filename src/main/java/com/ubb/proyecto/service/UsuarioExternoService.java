package com.ubb.proyecto.service;
import com.ubb.proyecto.model.UsuarioExterno;
import com.ubb.proyecto.repository.RepositorioUsuarioExterno;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class UsuarioExternoService {
    @Autowired
    private RepositorioUsuarioExterno usuarioExternoRepository;

    public List<UsuarioExterno> getAllUsuariosExternos(){
        return usuarioExternoRepository.findAll();
    }

    public Optional<UsuarioExterno> getUsuariosExternosByCorreo(String correo) {
        return usuarioExternoRepository.findById(correo);
    }

    // Actualiza Nombre
    public UsuarioExterno updateUsuarioExterno(String correo, UsuarioExterno usuarioExternoDetails){
        UsuarioExterno usuarioExterno = usuarioExternoRepository.findById(correo)
        .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        usuarioExterno.setNombre(usuarioExternoDetails.getNombre());

        return usuarioExternoRepository.save(usuarioExterno);
    }

    public void deleteUsuarioExterno(String correo){
        usuarioExternoRepository.deleteById(correo);
    }
    
    
    public Optional<UsuarioExterno> getUsuariosExternosById(String correo) {
        return usuarioExternoRepository.findById(correo);
    }


    public UsuarioExterno saveUsuarioExterno(UsuarioExterno usuarioExterno) {
        return usuarioExternoRepository.save(usuarioExterno);
    }
}
