package com.ubb.proyecto.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ubb.proyecto.model.Usuario;
import com.ubb.proyecto.repository.RepositorioUsuario;

@Service
public class UsuarioService {

    @Autowired
    private RepositorioUsuario usuarioRepository;

    public List<Usuario> getAllUsuarios(){
        return usuarioRepository.findAll();
    }

    public Optional<Usuario> getUsuariosById(Integer id) {
        return usuarioRepository.findById(id);
    }


    public Usuario saveUsuario(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    // Actualiza Nombre
    public Usuario updateUsuario(Integer id, Usuario usuarioDetails){
        Usuario usuario = usuarioRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        usuario.setNombre(usuarioDetails.getNombre());

        return usuarioRepository.save(usuario);
    }

    public void deleteUsuario(Integer Id){
        usuarioRepository.deleteById(Id);
    }
}