package com.ubb.proyecto;

import com.ubb.proyecto.model.Noticia;
import com.ubb.proyecto.repository.RepositorioNoticia;
import com.ubb.proyecto.service.NoticiaService;

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

public class NoticiaServiceTests {

    @Mock
    private RepositorioNoticia noticiaRepository;

    @InjectMocks
    private NoticiaService noticiaService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllNoticias() {
        // Arrange
        Noticia noticia1 = new Noticia(1, "Noticia 1", "Contenido 1");
        Noticia noticia2 = new Noticia(2, "Noticia 2", "Contenido 2");
        when(noticiaRepository.findAll()).thenReturn(Arrays.asList(noticia1, noticia2));

        // Act
        List<Noticia> result = noticiaService.getAllNoticias();

        // Assert
        assertNotNull(result);
        assertEquals(2, result.size());
        verify(noticiaRepository, times(1)).findAll();
    }

    @Test
    void testGetNoticiaById_Found() {
        // Arrange
        Noticia noticia = new Noticia(1, "Noticia de prueba", "Contenido de prueba");
        when(noticiaRepository.findById(1)).thenReturn(Optional.of(noticia));

        // Act
        Optional<Noticia> result = noticiaService.getNoticiaaById(1);

        // Assert
        assertTrue(result.isPresent());
        assertEquals("Noticia de prueba", result.get().getNombre());
        verify(noticiaRepository, times(1)).findById(1);
    }

    @Test
    void testGetNoticiaById_NotFound() {
        // Arrange
        when(noticiaRepository.findById(1)).thenReturn(Optional.empty());

        // Act
        Optional<Noticia> result = noticiaService.getNoticiaaById(1);

        // Assert
        assertFalse(result.isPresent());
        verify(noticiaRepository, times(1)).findById(1);
    }

    @Test
    void testSaveNoticia() {
        // Arrange
        Noticia noticia = new Noticia(1, "Noticia de prueba", "Contenido de prueba");
        when(noticiaRepository.save(noticia)).thenReturn(noticia);

        // Act
        Noticia result = noticiaService.saveNoticia(noticia);

        // Assert
        assertNotNull(result);
        assertEquals("Noticia de prueba", result.getNombre());
        verify(noticiaRepository, times(1)).save(noticia);
    }

    @Test
    void testDeleteNoticia() {
        // Act
        noticiaService.deleteNoticia(1);

        // Assert
        verify(noticiaRepository, times(1)).deleteById(1);
    }

    /* 
    @Test
    void testUpdateNoticia() {
        // Arrange
        Noticia existingNoticia = new Noticia(1, "Noticia antigua", "Contenido antiguo");
        Noticia updatedDetails = new Noticia(null, "Noticia actualizada", "Contenido actualizado");
        
        when(noticiaRepository.findById(1)).thenReturn(Optional.of(existingNoticia));
        when(noticiaRepository.save(any(Noticia.class))).thenReturn(updatedDetails);

        // Act
        Noticia result = noticiaService.updateNoticia(1, updatedDetails);

        // Assert
        assertNotNull(result);
        assertEquals("Noticia actualizada", result.getNombre());
        verify(noticiaRepository, times(1)).findById(1);
        verify(noticiaRepository, times(1)).save(existingNoticia);
    }
    */
}
