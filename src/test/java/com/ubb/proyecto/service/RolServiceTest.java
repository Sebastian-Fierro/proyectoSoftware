package com.ubb.proyecto.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.ubb.proyecto.model.Rol;
import com.ubb.proyecto.repository.RepositorioRol;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

class RolServiceTest {

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
        Rol rol1 = new Rol();
        Rol rol2 = new Rol();
        when(rolRepository.findAll()).thenReturn(Arrays.asList(rol1, rol2));

        List<Rol> roles = rolService.getAllRol();

        assertEquals(2, roles.size());
        verify(rolRepository, times(1)).findAll();
    }

    @Test
    void testGetRolById() {
        Rol rol = new Rol();
        rol.setId_rol(1);
        when(rolRepository.findById(1)).thenReturn(Optional.of(rol));

        Optional<Rol> result = rolService.getRolById(1);

        assertTrue(result.isPresent());
        assertEquals(1, result.get().getId_rol());
        verify(rolRepository, times(1)).findById(1);
    }

    @Test
    void testSaveRol() {
        Rol rol = new Rol();
        when(rolRepository.save(rol)).thenReturn(rol);

        Rol savedRol = rolService.saveRol(rol);

        assertNotNull(savedRol);
        verify(rolRepository, times(1)).save(rol);
    }

    @Test
    void testUpdateRol() {
        Rol existingRol = new Rol();
        existingRol.setId_rol(1);
        existingRol.setNombre("Old Name");

        Rol rolDetails = new Rol();
        rolDetails.setNombre("New Name");

        when(rolRepository.findById(1)).thenReturn(Optional.of(existingRol));
        when(rolRepository.save(existingRol)).thenReturn(existingRol);

        Rol updatedRol = rolService.updateRol(1, rolDetails);

        assertEquals("New Name", updatedRol.getNombre());
        verify(rolRepository, times(1)).findById(1);
        verify(rolRepository, times(1)).save(existingRol);
    }

    @Test
    void testDeleteRol() {
        rolService.deleteRol(1);
        verify(rolRepository, times(1)).deleteById(1);
    }
}
