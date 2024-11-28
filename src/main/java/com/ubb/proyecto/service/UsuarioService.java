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

    public Usuario updateUsuario(Integer id, Usuario usuarioDetails){
        Usuario usuario = usuarioRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        usuario.setNombre(usuarioDetails.getNombre());
        usuario.setCorreo(usuarioDetails.getCorreo());
        usuario.setContraseña(usuarioDetails.getContraseña());

        return usuarioRepository.save(usuario);
    }
    public boolean tienePermisoParaEditarNoticias(int usuarioId) {
        Usuario usuario = usuarioRepository.findById(usuarioId).orElse(null);
        if (usuario != null && usuario.getRol() != null) {
            return usuario.getRol().getNombre().equalsIgnoreCase("administrador") &&
                    usuario.getRol().getPermisos().stream()
                    .anyMatch(permiso -> permiso.getNombre().equals("EDITAR_NOTICIAS"));
        }
        return false;
    }

    public void deleteUsuario(Integer Id){
        usuarioRepository.deleteById(Id);
    }

    public boolean tienePermisoParaCrearNoticias(int usuarioId) {
        Usuario usuario = usuarioRepository.findById(usuarioId).orElse(null);
        if (usuario != null && usuario.getRol() != null) {
            return usuario.getRol().getPermisos().stream()
                            .anyMatch(permiso -> permiso.getNombre().equals("CREAR_NOTICIA"));
        }
        return false;
    }
    public boolean tienePermisoParaBorrarNoticias(int usuarioId) {
        Usuario usuario = usuarioRepository.findById(usuarioId).orElse(null);
        if (usuario != null && usuario.getRol() != null) {
            return usuario.getRol().getPermisos().stream().anyMatch(permiso -> permiso.getNombre().equals("BORRAR_NOTICIA"));
        }
        return false;
    }

<<<<<<< HEAD
    
=======
    public Usuario loginUsuario(Usuario usuario) {
        return usuarioRepository.findByCorreoAndContraseña(usuario.getCorreo(), usuario.getContraseña());
    }
>>>>>>> master
}