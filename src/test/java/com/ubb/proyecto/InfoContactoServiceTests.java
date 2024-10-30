package com.ubb.proyecto;

import com.ubb.proyecto.model.InfoContact;
import com.ubb.proyecto.repository.RepositorioInfoContact;
import com.ubb.proyecto.service.InfoContactoService;

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

public class InfoContactoServiceTests {

    @Mock
    private RepositorioInfoContact infoContactRepository;

    @InjectMocks
    private InfoContactoService infoContactoService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllInfoContacts() {
        // Arrange
        InfoContact contact1 = new InfoContact(1, "test1@example.com");
        InfoContact contact2 = new InfoContact(2, "test2@example.com");
        when(infoContactRepository.findAll()).thenReturn(Arrays.asList(contact1, contact2));

        // Act
        List<InfoContact> result = infoContactoService.getAllInfoContacts();

        // Assert
        assertNotNull(result);
        assertEquals(2, result.size());
        verify(infoContactRepository, times(1)).findAll();
    }

    @Test
    void testGetInfoContactById_Found() {
        // Arrange
        InfoContact contact = new InfoContact(1, "test@example.com");
        when(infoContactRepository.findById(1)).thenReturn(Optional.of(contact));

        // Act
        Optional<InfoContact> result = infoContactoService.getInfoContactById(1);

        // Assert
        assertTrue(result.isPresent());
        assertEquals("test@example.com", result.get().getCorreo());
        verify(infoContactRepository, times(1)).findById(1);
    }

    @Test
    void testGetInfoContactById_NotFound() {
        // Arrange
        when(infoContactRepository.findById(1)).thenReturn(Optional.empty());

        // Act
        Optional<InfoContact> result = infoContactoService.getInfoContactById(1);

        // Assert
        assertFalse(result.isPresent());
        verify(infoContactRepository, times(1)).findById(1);
    }

    @Test
    void testSaveInfoContact() {
        // Arrange
        InfoContact contact = new InfoContact(1, "test@example.com");
        when(infoContactRepository.save(contact)).thenReturn(contact);

        // Act
        InfoContact result = infoContactoService.saveInfoContact(contact);

        // Assert
        assertNotNull(result);
        assertEquals("test@example.com", result.getCorreo());
        verify(infoContactRepository, times(1)).save(contact);
    }

    @Test
    void testUpdateInfoContact() {
        // Arrange
        InfoContact existingContact = new InfoContact(1, "old@example.com");
        InfoContact updatedContact = new InfoContact(1, "new@example.com");
        when(infoContactRepository.findById(1)).thenReturn(Optional.of(existingContact));
        when(infoContactRepository.save(existingContact)).thenReturn(existingContact);

        // Act
        InfoContact result = infoContactoService.updateInfoContact(1, updatedContact);

        // Assert
        assertNotNull(result);
        assertEquals("new@example.com", result.getCorreo());
        verify(infoContactRepository, times(1)).findById(1);
        verify(infoContactRepository, times(1)).save(existingContact);
    }

    @Test
    void testDeleteInfoContact() {
        // Act
        infoContactoService.deleteInfoContact(1);

        // Assert
        verify(infoContactRepository, times(1)).deleteById(1);
    }
}
