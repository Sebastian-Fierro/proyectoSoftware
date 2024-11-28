package com.ubb.proyecto.rest;

import com.ubb.proyecto.controller.RolController;
import com.ubb.proyecto.model.Rol;
import com.ubb.proyecto.service.RolService;
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

import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class RolRestControllerTest {

    private MockMvc mockMvc;
    private JacksonTester<Rol> jsonRol;

    @Mock
    private RolService rolService;

    @InjectMocks
    private RolController rolController;

    @BeforeEach
    void setup() {
        JacksonTester.initFields(this, new ObjectMapper());
        mockMvc = MockMvcBuilders.standaloneSetup(rolController).build();
    }

    @Test
    void siInvocoGetAllRolsDebeRetornarListaDeRolesYStatusOk() throws Exception {
        // Given
        Rol rol1 = new Rol(1, "Admin");
        Rol rol2 = new Rol(2, "User");
        given(rolService.getAllRol()).willReturn(Arrays.asList(rol1, rol2));

        // When
        MockHttpServletResponse response = mockMvc.perform(MockMvcRequestBuilders.get("/rol")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn()
                .getResponse();

        // Then
        assertEquals(HttpStatus.OK.value(), response.getStatus());
        assertEquals("[{\"id\":1,\"nombre\":\"Admin\"},{\"id\":2,\"nombre\":\"User\"}]", response.getContentAsString());
    }

    @Test
    void siInvocoGetRolByIdYExisteDebeRetornarRolYStatusOk() throws Exception {
        // Given
        Rol rol = new Rol(1, "Admin");
        given(rolService.getRolById(1)).willReturn(Optional.of(rol));

        // When
        MockHttpServletResponse response = mockMvc.perform(MockMvcRequestBuilders.get("/rol/1")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn()
                .getResponse();

        // Then
        assertEquals(HttpStatus.OK.value(), response.getStatus());
        assertEquals(jsonRol.write(rol).getJson(), response.getContentAsString());
    }

    @Test
    void siInvocoGetRolByIdYNoExisteDebeRetornarStatusNotFound() throws Exception {
        // Given
        given(rolService.getRolById(1)).willReturn(Optional.empty());

        // When
        MockHttpServletResponse response = mockMvc.perform(MockMvcRequestBuilders.get("/rol/1")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn()
                .getResponse();

        // Then
        assertEquals(HttpStatus.NOT_FOUND.value(), response.getStatus());
    }

    @Test
    void siInvocoCreateRolDebeRetornarRolYStatusCreated() throws Exception {
        // Given
        Rol rol = new Rol(null, "New Role");
        Rol savedRol = new Rol(1, "New Role");
        given(rolService.saveRol(any(Rol.class))).willReturn(savedRol);

        // When
        MockHttpServletResponse response = mockMvc.perform(MockMvcRequestBuilders.post("/rol")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonRol.write(rol).getJson()))
                .andReturn()
                .getResponse();

        // Then
        assertEquals(HttpStatus.CREATED.value(), response.getStatus());
        assertEquals(jsonRol.write(savedRol).getJson(), response.getContentAsString());
    }

    @Test
    void siInvocoUpdateRolYExisteDebeRetornarRolActualizadoYStatusOk() throws Exception {
        // Given
        Rol rolDetails = new Rol(null, "Updated Role");
        Rol updatedRol = new Rol(1, "Updated Role");
        given(rolService.updateRol(1, rolDetails)).willReturn(updatedRol);

        // When
        MockHttpServletResponse response = mockMvc.perform(MockMvcRequestBuilders.put("/rol/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonRol.write(rolDetails).getJson()))
                .andReturn()
                .getResponse();

        // Then
        assertEquals(HttpStatus.OK.value(), response.getStatus());
        assertEquals(jsonRol.write(updatedRol).getJson(), response.getContentAsString());
    }

    @Test
    void siInvocoUpdateRolYNoExisteDebeRetornarStatusNotFound() throws Exception {
        // Given
        Rol rolDetails = new Rol(null, "Updated Role");
        given(rolService.updateRol(1, rolDetails)).willThrow(new RuntimeException("Rol not found"));

        // When
        MockHttpServletResponse response = mockMvc.perform(MockMvcRequestBuilders.put("/rol/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonRol.write(rolDetails).getJson()))
                .andReturn()
                .getResponse();

        // Then
        assertEquals(HttpStatus.NOT_FOUND.value(), response.getStatus());
    }

    @Test
    void siInvocoDeleteRolYExisteDebeRetornarStatusNoContent() throws Exception {
        // Given
        doNothing().when(rolService).deleteRol(1);

        // When
        MockHttpServletResponse response = mockMvc.perform(MockMvcRequestBuilders.delete("/rol/1"))
                .andReturn()
                .getResponse();

        // Then
        assertEquals(HttpStatus.NO_CONTENT.value(), response.getStatus());
    }

    @Test
    void siInvocoDeleteRolYNoExisteDebeRetornarStatusNotFound() throws Exception {
        // Given
        doThrow(new RuntimeException("Rol not found")).when(rolService).deleteRol(1);

        // When
        MockHttpServletResponse response = mockMvc.perform(MockMvcRequestBuilders.delete("/rol/1"))
                .andReturn()
                .getResponse();

        // Then
        assertEquals(HttpStatus.NOT_FOUND.value(), response.getStatus());
    }
}
