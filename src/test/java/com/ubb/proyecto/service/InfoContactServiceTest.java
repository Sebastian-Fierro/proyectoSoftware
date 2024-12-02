package com.ubb.proyecto.service;

import com.ubb.proyecto.model.InfoContact;
import com.ubb.proyecto.repository.RepositorioInfoContact;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class InfoContactServiceTest {

    @InjectMocks
    private InfoContactService infoContactService; 

    @Mock
    private RepositorioInfoContact infoContactRepository; 

    @Mock
    private UsuarioService usuarioService; 
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetInfoContactById_Success() {
        Integer id = 1;
        InfoContact mockContact = new InfoContact();
        mockContact.setId_contact(id);
        mockContact.setCorreo("test@example.com");
        when(infoContactRepository.findById(id)).thenReturn(Optional.of(mockContact));

        Optional<InfoContact> result = infoContactService.getInfoContactById(id);

        assertTrue(result.isPresent());
        assertEquals("test@example.com", result.get().getCorreo());
        verify(infoContactRepository, times(1)).findById(id); // Verifica que el método fue llamado
    }

    @Test
    void testGetInfoContactById_NotFound() {
        Integer id = 100;
        when(infoContactRepository.findById(id)).thenReturn(Optional.empty());

        Optional<InfoContact> result = infoContactService.getInfoContactById(id);

        assertFalse(result.isPresent());
        verify(infoContactRepository, times(1)).findById(id);
    }

    @Test
    void testSaveInfoContact_Success() {
        InfoContact contactToSave = new InfoContact();
        contactToSave.setCorreo("test@example.com");
        contactToSave.setTelefono(123456789);

        InfoContact savedContact = new InfoContact();
        savedContact.setId_contact(1);
        savedContact.setCorreo("test@example.com");
        savedContact.setTelefono(123456789);

        when(infoContactRepository.save(contactToSave)).thenReturn(savedContact);
        InfoContact result = infoContactService.saveInfoContact(contactToSave);
        assertNotNull(result);
        assertEquals(1, result.getIdContact());
        verify(infoContactRepository, times(1)).save(contactToSave);
    }

    @Test
    void testSaveInfoContact_MissingRequiredFields() {
        InfoContact invalidContact = new InfoContact();
        invalidContact.setTelefono(123456789); //Falta correo
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> infoContactService.saveInfoContact(invalidContact)
        );
        assertEquals("Los campos 'correo' y 'telefono' son obligatorios.", exception.getMessage());
    }

    @Test
    void testUpdateInfoContact_Success() {
        Integer id = 1;
        InfoContact existingContact = new InfoContact();
        existingContact.setId_contact(id);
        existingContact.setCorreo("old@example.com");
        existingContact.setTelefono(123456789);

        InfoContact updatedContact = new InfoContact();
        updatedContact.setCorreo("new@example.com");
        updatedContact.setTelefono(987654321);

        when(infoContactRepository.findById(id)).thenReturn(Optional.of(existingContact));
        when(infoContactRepository.save(existingContact)).thenReturn(existingContact);
        InfoContact result = infoContactService.updateInfoContact(id, updatedContact);
        assertNotNull(result);
        assertEquals("new@example.com", result.getCorreo());
        assertEquals(987654321, result.getTelefono());
        verify(infoContactRepository, times(1)).findById(id);
        verify(infoContactRepository, times(1)).save(existingContact);
    }

    @Test
    void testUpdateInfoContact_NotFound() {
        Integer id = 100;
        InfoContact updatedContact = new InfoContact();
        updatedContact.setCorreo("new@example.com");

        when(infoContactRepository.findById(id)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(
                RuntimeException.class,
                () -> infoContactService.updateInfoContact(id, updatedContact)
        );
        assertEquals("InfoContact no encontrado.", exception.getMessage());
        verify(infoContactRepository, times(1)).findById(id);
    }
}
