package com.ubb.proyecto.service;

import com.ubb.proyecto.model.Noticia;
import com.ubb.proyecto.repository.RepositorioNoticia;
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

class NoticiaServiceTest {

    @Mock
    private RepositorioNoticia noticiaRepository;

    @InjectMocks
    private NoticiaService noticiaService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllNoticias() {
        Noticia noticia1 = new Noticia();
        Noticia noticia2 = new Noticia();
        when(noticiaRepository.findAll()).thenReturn(Arrays.asList(noticia1, noticia2));

        List<Noticia> noticias = noticiaService.getAllNoticias();
        assertEquals(2, noticias.size());
        verify(noticiaRepository, times(1)).findAll();
    }

    @Test
    void getNoticiaById_found() {
        Noticia noticia = new Noticia();
        noticia.setIdNoticia(1);
        when(noticiaRepository.findById(1)).thenReturn(Optional.of(noticia));

        Optional<Noticia> foundNoticia = noticiaService.getNoticiaById(1);
        assertTrue(foundNoticia.isPresent());
        assertEquals(noticia.getIdNoticia(), foundNoticia.get().getIdNoticia());
        verify(noticiaRepository, times(1)).findById(1);
    }

    @Test
    void getNoticiaById_notFound() {
        when(noticiaRepository.findById(1)).thenReturn(Optional.empty());

        Optional<Noticia> foundNoticia = noticiaService.getNoticiaById(1);
        assertFalse(foundNoticia.isPresent());
        verify(noticiaRepository, times(1)).findById(1);
    }

    @Test
    void saveNoticia() {
        Noticia noticia = new Noticia();
        noticia.setTitulo("Título de prueba");
        noticia.setContenido("Contenido de prueba");
        when(noticiaRepository.save(noticia)).thenReturn(noticia);

        Noticia savedNoticia = noticiaService.saveNoticia(noticia);
        assertNotNull(savedNoticia);
        assertEquals("Título de prueba", savedNoticia.getTitulo());
        assertEquals("Contenido de prueba", savedNoticia.getContenido());
        verify(noticiaRepository, times(1)).save(noticia);
    }

    @Test
    void updateNoticia() {
        Noticia existingNoticia = new Noticia();
        existingNoticia.setIdNoticia(1);
        existingNoticia.setTitulo("Título anterior");
        existingNoticia.setContenido("Contenido anterior");

        Noticia updatedDetails = new Noticia();
        updatedDetails.setTitulo("Nuevo título");
        updatedDetails.setContenido("Nuevo contenido");

        when(noticiaRepository.findById(1)).thenReturn(Optional.of(existingNoticia));
        when(noticiaRepository.save(existingNoticia)).thenReturn(existingNoticia);

        Noticia updatedNoticia = noticiaService.updateNoticia(1, updatedDetails);

        assertEquals("Nuevo título", updatedNoticia.getTitulo());
        assertEquals("Nuevo contenido", updatedNoticia.getContenido());
        verify(noticiaRepository, times(1)).findById(1);
        verify(noticiaRepository, times(1)).save(existingNoticia);
    }

    @Test
    void deleteNoticia() {
        int id = 1;
        doNothing().when(noticiaRepository).deleteById(id);

        noticiaService.deleteNoticia(id);
        verify(noticiaRepository, times(1)).deleteById(id);
    }
}
