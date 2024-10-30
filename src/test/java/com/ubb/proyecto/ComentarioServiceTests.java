package com.ubb.proyecto;

import com.ubb.proyecto.model.Comentario;
import com.ubb.proyecto.repository.RepositorioComentario;
import com.ubb.proyecto.service.ComentarioService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ComentarioServiceTests {
    
    @Mock
    private RepositorioComentario comentarioRepository;

    @InjectMocks
    private ComentarioService comentarioService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllComentarios() {
        // Arrange
        Comentario comentario1 = new Comentario(1, "Texto 1");
        Comentario comentario2 = new Comentario(2, "Texto 2");
        when(comentarioRepository.findAll()).thenReturn(Arrays.asList(comentario1, comentario2));

        // Act
        List<Comentario> result = comentarioService.getAllComentarios();

        // Assert
        assertNotNull(result);
        assertEquals(2, result.size());
        verify(comentarioRepository, times(1)).findAll();
    }

    @Test
    void testGetComentarioById_Found() {
        // Arrange
        Comentario comentario = new Comentario(1, "Texto de prueba");
        when(comentarioRepository.findById(1)).thenReturn(Optional.of(comentario));

        // Act
        Optional<Comentario> result = comentarioService.getComentarioById(1);

        // Assert
        assertTrue(result.isPresent());
        assertEquals("Texto de prueba", result.get().getTexto());
        verify(comentarioRepository, times(1)).findById(1);
    }

    @Test
    void testGetComentarioById_NotFound() {
        // Arrange
        when(comentarioRepository.findById(1)).thenReturn(Optional.empty());

        // Act
        Optional<Comentario> result = comentarioService.getComentarioById(1);

        // Assert
        assertFalse(result.isPresent());
        verify(comentarioRepository, times(1)).findById(1);
    }

    @Test
    void testSaveComentario() {
        // Arrange
        Comentario comentario = new Comentario(1, "Texto de prueba");
        when(comentarioRepository.save(comentario)).thenReturn(comentario);

        // Act
        Comentario result = comentarioService.savComentario(comentario);

        // Assert
        assertNotNull(result);
        assertEquals("Texto de prueba", result.getTexto());
        verify(comentarioRepository, times(1)).save(comentario);
    }

    @Test
    void testDeleteComentario() {
        // Act
        comentarioService.deleteComentario(1);

        // Assert
        verify(comentarioRepository, times(1)).deleteById(1);
    }
}
