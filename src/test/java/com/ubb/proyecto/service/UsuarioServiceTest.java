package com.ubb.proyecto.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.ubb.proyecto.model.Permiso;
import com.ubb.proyecto.model.Rol;
import com.ubb.proyecto.model.Usuario;
import com.ubb.proyecto.repository.RepositorioUsuario;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

class UsuarioServiceTest {

    @Mock
    private RepositorioUsuario usuarioRepository;

    @InjectMocks
    private UsuarioService usuarioService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllUsuarios() {
        Usuario usuario1 = new Usuario();
        Usuario usuario2 = new Usuario();
        when(usuarioRepository.findAll()).thenReturn(Arrays.asList(usuario1, usuario2));

        List<Usuario> usuarios = usuarioService.getAllUsuarios();

        assertEquals(2, usuarios.size());
        verify(usuarioRepository, times(1)).findAll();
    }

    @Test
    void testGetUsuariosById() {
        Usuario usuario = new Usuario();
        usuario.setId_user(1);
        when(usuarioRepository.findById(1)).thenReturn(Optional.of(usuario));

        Optional<Usuario> result = usuarioService.getUsuariosById(1);

        assertTrue(result.isPresent());
        assertEquals(1, result.get().getId_user());
        verify(usuarioRepository, times(1)).findById(1);
    }

    @Test
    void testSaveUsuario() {
        Usuario usuario = new Usuario();
        when(usuarioRepository.save(usuario)).thenReturn(usuario);

        Usuario savedUsuario = usuarioService.saveUsuario(usuario);

        assertNotNull(savedUsuario);
        verify(usuarioRepository, times(1)).save(usuario);
    }

    @Test
    void testUpdateUsuario() {
        Usuario existingUsuario = new Usuario();
        existingUsuario.setId_user(1);
        existingUsuario.setNombre("Old Name");

        Usuario usuarioDetails = new Usuario();
        usuarioDetails.setNombre("New Name");
        usuarioDetails.setCorreo("new@example.com");
        usuarioDetails.setContraseña("newpassword");

        when(usuarioRepository.findById(1)).thenReturn(Optional.of(existingUsuario));
        when(usuarioRepository.save(existingUsuario)).thenReturn(existingUsuario);

        Usuario updatedUsuario = usuarioService.updateUsuario(1, usuarioDetails);

        assertEquals("New Name", updatedUsuario.getNombre());
        assertEquals("new@example.com", updatedUsuario.getCorreo());
        verify(usuarioRepository, times(1)).findById(1);
        verify(usuarioRepository, times(1)).save(existingUsuario);
    }

    @Test
    void testTienePermisoParaEditarNoticias() {
        Usuario usuario = new Usuario();
        Rol rol = new Rol();
        rol.setNombre("Administrador");

        Permiso permiso = new Permiso();
        permiso.setNombre("EDITAR_NOTICIAS");
        rol.setPermisos(Arrays.asList(permiso));

        usuario.setRol(rol);
        when(usuarioRepository.findById(1)).thenReturn(Optional.of(usuario));

        boolean resultado = usuarioService.tienePermisoParaEditarNoticias(1);

        assertTrue(resultado);
        verify(usuarioRepository, times(1)).findById(1);
    }

    @Test
    void testTienePermisoParaCrearNoticias() {
        Usuario usuario = new Usuario();
        Rol rol = new Rol();

        Permiso permiso = new Permiso();
        permiso.setNombre("CREAR_NOTICIA");
        rol.setPermisos(Arrays.asList(permiso));

        usuario.setRol(rol);
        when(usuarioRepository.findById(1)).thenReturn(Optional.of(usuario));

        boolean resultado = usuarioService.tienePermisoParaCrearNoticias(1);

        assertTrue(resultado);
        verify(usuarioRepository, times(1)).findById(1);
    }

    @Test
    void testTienePermisoParaBorrarNoticias() {
        Usuario usuario = new Usuario();
        Rol rol = new Rol();

        Permiso permiso = new Permiso();
        permiso.setNombre("BORRAR_NOTICIA");
        rol.setPermisos(Arrays.asList(permiso));

        usuario.setRol(rol);
        when(usuarioRepository.findById(1)).thenReturn(Optional.of(usuario));

        boolean resultado = usuarioService.tienePermisoParaBorrarNoticias(1);

        assertTrue(resultado);
        verify(usuarioRepository, times(1)).findById(1);
    }

    @Test
    void testDeleteUsuario() {
        usuarioService.deleteUsuario(1);
        verify(usuarioRepository, times(1)).deleteById(1);
    }
}
