package com.ubb.proyecto;

import com.ubb.proyecto.model.Usuario;
import com.ubb.proyecto.repository.RepositorioUsuario;
import com.ubb.proyecto.service.UsuarioService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class UsuarioServiceTests {

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
        // Arrange
        Usuario usuario1 = new Usuario(1, "Usuario 1");
        Usuario usuario2 = new Usuario(2, "Usuario 2");
        when(usuarioRepository.findAll()).thenReturn(Arrays.asList(usuario1, usuario2));

        // Act
        List<Usuario> result = usuarioService.getAllUsuarios();

        // Assert
        assertNotNull(result);
        assertEquals(2, result.size());
        verify(usuarioRepository, times(1)).findAll();
    }

    @Test
    void testGetUsuariosById_Found() {
        // Arrange
        Usuario usuario = new Usuario(1, "Usuario de prueba");
        when(usuarioRepository.findById(1)).thenReturn(Optional.of(usuario));

        // Act
        Optional<Usuario> result = usuarioService.getUsuariosById(1);

        // Assert
        assertTrue(result.isPresent());
        assertEquals("Usuario de prueba", result.get().getNombre());
        verify(usuarioRepository, times(1)).findById(1);
    }

    @Test
    void testGetUsuariosById_NotFound() {
        // Arrange
        when(usuarioRepository.findById(1)).thenReturn(Optional.empty());

        // Act
        Optional<Usuario> result = usuarioService.getUsuariosById(1);

        // Assert
        assertFalse(result.isPresent());
        verify(usuarioRepository, times(1)).findById(1);
    }

    @Test
    void testSaveUsuario() {
        // Arrange
        Usuario usuario = new Usuario(1, "Usuario de prueba");
        when(usuarioRepository.save(usuario)).thenReturn(usuario);

        // Act
        Usuario result = usuarioService.saveUsuario(usuario);

        // Assert
        assertNotNull(result);
        assertEquals("Usuario de prueba", result.getNombre());
        verify(usuarioRepository, times(1)).save(usuario);
    }

    @Test
    void testUpdateUsuario() {
        // Arrange
        Usuario existingUsuario = new Usuario(1, "Usuario viejo");
        Usuario updatedUsuario = new Usuario(1, "Usuario actualizado");
        when(usuarioRepository.findById(1)).thenReturn(Optional.of(existingUsuario));
        when(usuarioRepository.save(existingUsuario)).thenReturn(existingUsuario);

        // Act
        Usuario result = usuarioService.updateUsuario(1, updatedUsuario);

        // Assert
        assertNotNull(result);
        assertEquals("Usuario actualizado", result.getNombre());
        verify(usuarioRepository, times(1)).findById(1);
        verify(usuarioRepository, times(1)).save(existingUsuario);
    }

    @Test
    void testDeleteUsuario() {
        // Act
        usuarioService.deleteUsuario(1);

        // Assert
        verify(usuarioRepository, times(1)).deleteById(1);
    }
}
