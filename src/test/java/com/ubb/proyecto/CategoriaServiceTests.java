package com.ubb.proyecto;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.ubb.proyecto.model.Categoria;
import com.ubb.proyecto.repository.RepositorioCategoria;
import com.ubb.proyecto.service.CategoriaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.*;

public class CategoriaServiceTests {
    
    @Mock
    private RepositorioCategoria categoriaRepository;

    @InjectMocks
    private CategoriaService categoriaService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllCategorias() {
        Categoria categoria1 = new Categoria();
        categoria1.setNombre("Categoria 1");

        Categoria categoria2 = new Categoria();
        categoria2.setNombre("Categoria 2");

        when(categoriaRepository.findAll()).thenReturn(Arrays.asList(categoria1, categoria2));

        List<Categoria> categorias = categoriaService.getAllCategorias();
        assertEquals(2, categorias.size());
        assertEquals("Categoria 1", categorias.get(0).getNombre());
        assertEquals("Categoria 2", categorias.get(1).getNombre());
    }

    @Test
    void testGetCategoriaById() {
        Categoria categoria = new Categoria();
        categoria.setNombre("Categoria 1");

        when(categoriaRepository.findById(1)).thenReturn(Optional.of(categoria));

        Optional<Categoria> result = categoriaService.getCategoriaById(1);
        assertTrue(result.isPresent());
        assertEquals("Categoria 1", result.get().getNombre());
    }

    @Test
    void testSaveCategoria() {
        Categoria categoria = new Categoria();
        categoria.setNombre("Nueva Categoria");

        when(categoriaRepository.save(categoria)).thenReturn(categoria);

        Categoria result = categoriaService.saveCategoria(categoria);
        assertNotNull(result);
        assertEquals("Nueva Categoria", result.getNombre());
    }

    @Test
    void testUpdateCategoria() {
        Categoria existingCategoria = new Categoria();
        existingCategoria.setNombre("Categoria Antigua");

        Categoria newCategoriaDetails = new Categoria();
        newCategoriaDetails.setNombre("Categoria Actualizada");

        when(categoriaRepository.findById(1)).thenReturn(Optional.of(existingCategoria));
        when(categoriaRepository.save(existingCategoria)).thenReturn(existingCategoria);

        Categoria result = categoriaService.updateCategoria(1, newCategoriaDetails);
        assertNotNull(result);
        assertEquals("Categoria Actualizada", result.getNombre());
    }

    @Test
    void testUpdateCategoriaNotFound() {
        Categoria newCategoriaDetails = new Categoria();
        newCategoriaDetails.setNombre("Categoria Nueva");

        when(categoriaRepository.findById(1)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> {
            categoriaService.updateCategoria(1, newCategoriaDetails);
        });
    }

    @Test
    void testDeleteCategoria() {
        doNothing().when(categoriaRepository).deleteById(1);

        categoriaService.deleteCategoria(1);

        verify(categoriaRepository, times(1)).deleteById(1);
    }
}

