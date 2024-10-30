package com.ubb.proyecto;

import com.ubb.proyecto.model.Rol;
import com.ubb.proyecto.repository.RepositorioRol;
import com.ubb.proyecto.service.RolService;
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

public class RolServiceTests {

    @Mock
    private RepositorioRol rolRepository;

    @InjectMocks
    private RolService rolService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllRol() {
        // Arrange
        Rol rol1 = new Rol(1, "Admin");
        Rol rol2 = new Rol(2, "User");
        when(rolRepository.findAll()).thenReturn(Arrays.asList(rol1, rol2));

        // Act
        List<Rol> result = rolService.getAllRol();

        // Assert
        assertNotNull(result);
        assertEquals(2, result.size());
        verify(rolRepository, times(1)).findAll();
    }

    @Test
    void testGetRolById_Found() {
        // Arrange
        Rol rol = new Rol(1, "Admin");
        when(rolRepository.findById(1)).thenReturn(Optional.of(rol));

        // Act
        Optional<Rol> result = rolService.getRolById(1);

        // Assert
        assertTrue(result.isPresent());
        assertEquals("Admin", result.get().getNombre());
        verify(rolRepository, times(1)).findById(1);
    }

    @Test
    void testGetRolById_NotFound() {
        // Arrange
        when(rolRepository.findById(1)).thenReturn(Optional.empty());

        // Act
        Optional<Rol> result = rolService.getRolById(1);

        // Assert
        assertFalse(result.isPresent());
        verify(rolRepository, times(1)).findById(1);
    }

    @Test
    void testUpdateRol() {
        // Arrange
        Rol existingRol = new Rol(1, "Admin");
        Rol updatedRol = new Rol(1, "Super Admin");
        when(rolRepository.findById(1)).thenReturn(Optional.of(existingRol));
        when(rolRepository.save(existingRol)).thenReturn(existingRol);

        // Act
        Rol result = rolService.updateRol(1, updatedRol);

        // Assert
        assertNotNull(result);
        assertEquals("Super Admin", result.getNombre());
        verify(rolRepository, times(1)).findById(1);
        verify(rolRepository, times(1)).save(existingRol);
    }

    @Test
    void testDeleteRol() {
        // Act
        rolService.deleteRol(1);

        // Assert
        verify(rolRepository, times(1)).deleteById(1);
    }
}
