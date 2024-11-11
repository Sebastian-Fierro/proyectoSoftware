package com.ubb.proyecto.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.ubb.proyecto.model.Permiso;
import com.ubb.proyecto.repository.RepositorioPermiso;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

class PermisoServiceTest {

    @Mock
    private RepositorioPermiso permisoRepository;

    @InjectMocks
    private PermisoService permisoService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllPermisos() {
        Permiso permiso1 = new Permiso();
        Permiso permiso2 = new Permiso();
        when(permisoRepository.findAll()).thenReturn(Arrays.asList(permiso1, permiso2));

        List<Permiso> permisos = permisoService.getAllPermisos();

        assertEquals(2, permisos.size());
        verify(permisoRepository, times(1)).findAll();
    }

    @Test
    void testGetPermisoById() {
        Permiso permiso = new Permiso();
        permiso.setIdPermiso(1);
        when(permisoRepository.findById(1)).thenReturn(Optional.of(permiso));

        Optional<Permiso> result = permisoService.getPermisoById(1);

        assertTrue(result.isPresent());
        assertEquals(1, result.get().getIdPermiso());
        verify(permisoRepository, times(1)).findById(1);
    }

    @Test
    void testSavePermiso() {
        Permiso permiso = new Permiso();
        when(permisoRepository.save(permiso)).thenReturn(permiso);

        Permiso savedPermiso = permisoService.savePermiso(permiso);

        assertNotNull(savedPermiso);
        verify(permisoRepository, times(1)).save(permiso);
    }

    @Test
    void testUpdatePermiso() {
        Permiso existingPermiso = new Permiso();
        existingPermiso.setIdPermiso(1);
        existingPermiso.setNombre("Old Name");

        Permiso permisoDetails = new Permiso();
        permisoDetails.setNombre("New Name");

        when(permisoRepository.findById(1)).thenReturn(Optional.of(existingPermiso));
        when(permisoRepository.save(existingPermiso)).thenReturn(existingPermiso);

        Permiso updatedPermiso = permisoService.updatePermiso(1, permisoDetails);

        assertEquals("New Name", updatedPermiso.getNombre());
        verify(permisoRepository, times(1)).findById(1);
        verify(permisoRepository, times(1)).save(existingPermiso);
    }

    @Test
    void testDeletePermiso() {
        permisoService.deletePermiso(1);
        verify(permisoRepository, times(1)).deleteById(1);
    }
}
