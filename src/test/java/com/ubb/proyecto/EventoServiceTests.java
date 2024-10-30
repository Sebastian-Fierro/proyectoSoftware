package com.ubb.proyecto;

import com.ubb.proyecto.model.Evento;
import com.ubb.proyecto.repository.RepositorioEvento;
import com.ubb.proyecto.service.EventoService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class EventoServiceTests {

    @Mock
    private RepositorioEvento eventoRepository;

    @InjectMocks
    private EventoService eventoService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllEventos() {
        // Arrange
        Evento evento1 = new Evento(1, "Evento 1", "Lugar 1", LocalTime.of(10, 0));
        Evento evento2 = new Evento(2, "Evento 2", "Lugar 2", LocalTime.of(12, 0));
        when(eventoRepository.findAll()).thenReturn(Arrays.asList(evento1, evento2));

        // Act
        List<Evento> result = eventoService.getAllEventos();

        // Assert
        assertNotNull(result);
        assertEquals(2, result.size());
        verify(eventoRepository, times(1)).findAll();
    }

    @Test
    void testGetEventoById_Found() {
        // Arrange
        Evento evento = new Evento(1, "Evento de prueba", "Lugar de prueba", LocalTime.of(15, 0));
        when(eventoRepository.findById(1)).thenReturn(Optional.of(evento));

        // Act
        Optional<Evento> result = eventoService.getEventoById(1);

        // Assert
        assertTrue(result.isPresent());
        assertEquals("Evento de prueba", result.get().getNombre());
        verify(eventoRepository, times(1)).findById(1);
    }

    @Test
    void testGetEventoById_NotFound() {
        // Arrange
        when(eventoRepository.findById(1)).thenReturn(Optional.empty());

        // Act
        Optional<Evento> result = eventoService.getEventoById(1);

        // Assert
        assertFalse(result.isPresent());
        verify(eventoRepository, times(1)).findById(1);
    }

    @Test
    void testGetEventoByLugar() {
        // Arrange
        Evento evento1 = new Evento(1, "Evento 1", "Lugar 1", LocalTime.of(10, 0));
        Evento evento2 = new Evento(2, "Evento 2", "Lugar 1", LocalTime.of(12, 0));
        when(eventoRepository.findByLugar("Lugar 1")).thenReturn(Arrays.asList(evento1, evento2));

        // Act
        List<Evento> result = eventoService.getEventoByLugar("Lugar 1");

        // Assert
        assertNotNull(result);
        assertEquals(2, result.size());
        verify(eventoRepository, times(1)).findByLugar("Lugar 1");
    }

    @Test
    void testGetEventoByHorario() {
        // Arrange
        Evento evento1 = new Evento(1, "Evento 1", "Lugar 1", LocalTime.of(10, 0));
        when(eventoRepository.findByHorario(LocalTime.of(10, 0))).thenReturn(Arrays.asList(evento1));

        // Act
        List<Evento> result = eventoService.getEventoByHorario(LocalTime.of(10, 0));

        // Assert
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("Evento 1", result.get(0).getNombre());
        verify(eventoRepository, times(1)).findByHorario(LocalTime.of(10, 0));
    }

    @Test
    void testSaveEvento() {
        // Arrange
        Evento evento = new Evento(1, "Evento de prueba", "Lugar de prueba", LocalTime.of(15, 0));
        when(eventoRepository.save(evento)).thenReturn(evento);

        // Act
        Evento result = eventoService.saveEvento(evento);

        // Assert
        assertNotNull(result);
        assertEquals("Evento de prueba", result.getNombre());
        verify(eventoRepository, times(1)).save(evento);
    }

    @Test
    void testDeleteEvento() {
        // Act
        eventoService.deleteEvento(1);

        // Assert
        verify(eventoRepository, times(1)).deleteById(1);
    }
}
