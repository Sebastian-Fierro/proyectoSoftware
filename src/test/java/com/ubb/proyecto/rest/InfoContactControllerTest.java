package com.ubb.proyecto.rest;

import com.ubb.proyecto.model.InfoContact;
import com.ubb.proyecto.service.InfoContactService;
import com.ubb.proyecto.controller.InfoContactController;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class InfoContactControllerTest {

    private JacksonTester<InfoContact> jsonInfoContact;
    private MockMvc mockMvc;

    @Mock
    private InfoContactService infoContactService;

    @InjectMocks
    private InfoContactController infoContactController;

    @BeforeEach
    void setup() {
        JacksonTester.initFields(this, new ObjectMapper());
        mockMvc = MockMvcBuilders.standaloneSetup(infoContactController).build();
    }

    @Test
    void siInvocoGetInfoContactPorIdDebeDevolverStatusOk() throws Exception {
        // Given
        InfoContact contact = new InfoContact();
        contact.setId_contact(1);
        contact.setCorreo("test@example.com");
        contact.setTelefono(123456789);
        contact.setInstagram("@test");
        contact.setFacebook("facebook.com/test");

        given(infoContactService.getInfoContactById(1)).willReturn(Optional.of(contact));

        // When
        MockHttpServletResponse response = mockMvc.perform(MockMvcRequestBuilders.get("/infoContacto/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andReturn()
                .getResponse();

        // Then
        assertEquals(HttpStatus.OK.value(), response.getStatus());
        assertEquals(jsonInfoContact.write(contact).getJson(), response.getContentAsString());
        verify(infoContactService, times(1)).getInfoContactById(1);
    }

    @Test
    void siInvocoGetInfoContactPorIdYNoExisteDebeDevolverStatusNotFound() throws Exception {
        // Given
        given(infoContactService.getInfoContactById(1)).willReturn(Optional.empty());

        // When
        MockHttpServletResponse response = mockMvc.perform(MockMvcRequestBuilders.get("/infoContacto/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andReturn()
                .getResponse();

        // Then
        assertEquals(HttpStatus.NOT_FOUND.value(), response.getStatus());
        verify(infoContactService, times(1)).getInfoContactById(1);
    }

    @Test
    void siInvocoCrearInfoContactDebeDevolverStatusOk() throws Exception {
        // Given
        InfoContact contact = new InfoContact();
        contact.setCorreo("test@example.com");
        contact.setTelefono(987654321);
        contact.setInstagram("@new");
        contact.setFacebook("facebook.com/new");

        given(infoContactService.saveInfoContact(contact)).willReturn(contact);

        // When
        MockHttpServletResponse response = mockMvc.perform(MockMvcRequestBuilders.post("/infoContacto/insert/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonInfoContact.write(contact).getJson()))
                .andReturn()
                .getResponse();

        // Then
        assertEquals(HttpStatus.CREATED.value(), response.getStatus());
        verify(infoContactService, times(1)).saveInfoContact(contact);
    }

    @Test
    void siInvocoActualizarInfoContactDebeDevolverStatusOk() throws Exception {
        // Given
        InfoContact existingContact = new InfoContact();
        existingContact.setId_contact(1);
        existingContact.setCorreo("old@example.com");
        existingContact.setTelefono(123456789);

        InfoContact updatedContact = new InfoContact();
        updatedContact.setCorreo("updated@example.com");
        updatedContact.setTelefono(987654321);

        given(infoContactService.getInfoContactById(1)).willReturn(Optional.of(existingContact));
        given(infoContactService.saveInfoContact(existingContact)).willReturn(existingContact);

        // When
        MockHttpServletResponse response = mockMvc.perform(MockMvcRequestBuilders.put("/infoContacto/update/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonInfoContact.write(updatedContact).getJson()))
                .andReturn()
                .getResponse();

        // Then
        assertEquals(HttpStatus.OK.value(), response.getStatus());
        verify(infoContactService, times(1)).getInfoContactById(1);
        verify(infoContactService, times(1)).saveInfoContact(existingContact);
    }

    @Test
    void siInvocoEliminarInfoContactDebeDevolverStatusNoContent() throws Exception {
        // Given
        given(infoContactService.getInfoContactById(1)).willReturn(Optional.of(new InfoContact()));
        // Do nothing al eliminar

        // When
        MockHttpServletResponse response = mockMvc.perform(MockMvcRequestBuilders.delete("/infoContacto/1"))
                .andReturn()
                .getResponse();

        // Then
        assertEquals(HttpStatus.NO_CONTENT.value(), response.getStatus());
        verify(infoContactService, times(1)).deleteInfoContact(1);
    }
}
