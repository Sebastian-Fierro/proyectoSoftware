package com.ubb.proyecto.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.ubb.proyecto.model.Categoria;
import com.ubb.proyecto.repository.RepositorioCategoria;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

class CategoriaServiceTest {

    @Mock
    private RepositorioCategoria repositorio;

    @InjectMocks
    private CategoriaService categoriaService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllCategorias() {
        Categoria categoria1 = new Categoria();
        Categoria categoria2 = new Categoria();
        when(repositorio.findAll()).thenReturn(Arrays.asList(categoria1, categoria2));

        List<Categoria> categorias = categoriaService.getAllCategorias();

        assertEquals(2, categorias.size());
        verify(repositorio, times(1)).findAll();
    }

    @Test
    void testExistCategoriaById() {
        when(repositorio.existsById(1)).thenReturn(true);

        boolean exists = categoriaService.existCategoriaById(1);

        assertTrue(exists);
        verify(repositorio, times(1)).existsById(1);
    }

    @Test
    void testGetCategoriaById() {
        Categoria categoria = new Categoria();
        categoria.setIdCategory(1);
        when(repositorio.findById(1)).thenReturn(Optional.of(categoria));

        Optional<Categoria> result = categoriaService.getCategoriaById(1);

        assertTrue(result.isPresent());
        assertEquals(1, result.get().getIdCategory());
        verify(repositorio, times(1)).findById(1);
    }

    @Test
    void testSaveCategoria() {
        Categoria categoria = new Categoria();
        when(repositorio.save(categoria)).thenReturn(categoria);

        Categoria savedCategoria = categoriaService.saveCategoria(categoria);

        assertNotNull(savedCategoria);
        verify(repositorio, times(1)).save(categoria);
    }

    @Test
    void testUpdateCategoria() {
        Categoria existingCategoria = new Categoria();
        existingCategoria.setIdCategory(1);
        existingCategoria.setNombre("Old Name");

        Categoria categoriaDetails = new Categoria();
        categoriaDetails.setNombre("New Name");

        when(repositorio.findById(1)).thenReturn(Optional.of(existingCategoria));
        when(repositorio.save(existingCategoria)).thenReturn(existingCategoria);

        Categoria updatedCategoria = categoriaService.updateCategoria(1, categoriaDetails);

        assertEquals("New Name", updatedCategoria.getNombre());
        verify(repositorio, times(1)).findById(1);
        verify(repositorio, times(1)).save(existingCategoria);
    }

    @Test
    void testDeleteCategoria() {
        Categoria categoria = new Categoria();
        categoria.setIdCategory(1);

        when(repositorio.findById(1)).thenReturn(Optional.of(categoria));

        categoriaService.deleteCategoria(1);

        verify(repositorio, times(1)).findById(1);
        verify(repositorio, times(1)).deleteById(1);
    }

    @Test
    void testDeleteCategoria_NotFound() {
        when(repositorio.findById(1)).thenReturn(Optional.empty());

        Exception exception = assertThrows(RuntimeException.class, () -> categoriaService.deleteCategoria(1));

        assertEquals("Categoria no encontrada", exception.getMessage());
        verify(repositorio, times(1)).findById(1);
        verify(repositorio, never()).deleteById(1);
    }
}
