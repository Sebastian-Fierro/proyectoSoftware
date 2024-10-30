package com.ubb.proyecto;

import com.ubb.proyecto.model.Permiso;
import com.ubb.proyecto.repository.RepositorioPermiso;
import com.ubb.proyecto.service.PermisoService;
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

public class PermisoServiceTests {

    @Mock
    private RepositorioPermiso permisoRepository;

    @InjectMocks
    private PermisoService permisoService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllPermiso() {
        // Arrange
        Permiso permiso1 = new Permiso(1, "Permiso 1");
        Permiso permiso2 = new Permiso(2, "Permiso 2");
        when(permisoRepository.findAll()).thenReturn(Arrays.asList(permiso1, permiso2));

        // Act
        List<Permiso> result = permisoService.getAllPermisos();

        // Assert
        assertNotNull(result);
        assertEquals(2, result.size());
        verify(permisoRepository, times(1)).findAll();
    }

    @Test
    void testGetPermisoById_Found() {
        // Arrange
        Permiso permiso = new Permiso(1, "Permiso de prueba");
        when(permisoRepository.findById(1)).thenReturn(Optional.of(permiso));

        // Act
        Optional<Permiso> result = permisoService.getPermisoById(1);

        // Assert
        assertTrue(result.isPresent());
        assertEquals("Permiso de prueba", result.get().getNombre());
        verify(permisoRepository, times(1)).findById(1);
    }

    @Test
    void testGetPermisoById_NotFound() {
        // Arrange
        when(permisoRepository.findById(1)).thenReturn(Optional.empty());

        // Act
        Optional<Permiso> result = permisoService.getPermisoById(1);

        // Assert
        assertFalse(result.isPresent());
        verify(permisoRepository, times(1)).findById(1);
    }

    @Test
    void testSavePermiso() {
        // Arrange
        Permiso permiso = new Permiso(1, "Permiso de prueba");
        when(permisoRepository.save(permiso)).thenReturn(permiso);

        // Act
        Permiso result = permisoService.savePermiso(permiso);

        // Assert
        assertNotNull(result);
        assertEquals("Permiso de prueba", result.getNombre());
        verify(permisoRepository, times(1)).save(permiso);
    }

    @Test
    void testUpdatePermiso() {
        // Arrange
        Permiso existingPermiso = new Permiso(1, "Permiso viejo");
        Permiso updatedPermiso = new Permiso(1, "Permiso actualizado");
        when(permisoRepository.findById(1)).thenReturn(Optional.of(existingPermiso));
        when(permisoRepository.save(existingPermiso)).thenReturn(updatedPermiso);

        // Act
        Permiso result = permisoService.updatePermiso(1, updatedPermiso);

        // Assert
        assertNotNull(result);
        assertEquals("Permiso actualizado", result.getNombre());
        verify(permisoRepository, times(1)).findById(1);
        verify(permisoRepository, times(1)).save(existingPermiso);
    }

    @Test
    void testDeletePermiso() {
        // Act
        permisoService.deletePermiso(1);

        // Assert
        verify(permisoRepository, times(1)).deleteById(1);
    }
}
