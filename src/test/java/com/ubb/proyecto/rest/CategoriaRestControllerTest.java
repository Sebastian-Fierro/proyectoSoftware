package com.ubb.proyecto.rest;

import com.ubb.proyecto.model.Categoria;
import com.ubb.proyecto.service.CategoriaService;
import com.ubb.proyecto.controller.*;

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
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Optional;

import static org.mockito.BDDMockito.given;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@ExtendWith(MockitoExtension.class)
public class CategoriaRestControllerTest {

    private JacksonTester<Categoria> jsonCategoria;
    private MockMvc mockMvc;

    @Mock
    private CategoriaService servicio;

    @InjectMocks
    private CategoriaController categoriaController;

    @BeforeEach
    void setup() {
        JacksonTester.initFields(this, new ObjectMapper());
        mockMvc = MockMvcBuilders.standaloneSetup(categoriaController).build();
    }

    @Test
    void siInvocoGetCategoriaConUnIdYExisteUnaCategoriaConEseIdDebeDevolverCategoriaYStatusOk() throws Exception {
        // Given
        Categoria categoria = new Categoria();
        categoria.setIdCategory(1);
        categoria.setNombre("Electronica");
        given(servicio.getCategoriaById(1)).willReturn(Optional.of(categoria));

        // When
        MockHttpServletResponse response = mockMvc.perform(get("/categorias/1")
                .accept(MediaType.APPLICATION_JSON))
                .andReturn()
                .getResponse();

        // Then
        assertEquals(HttpStatus.OK.value(), response.getStatus());
        assertEquals(jsonCategoria.write(categoria).getJson(), response.getContentAsString());
    }

    @Test
    void siInvocoGetCategoriaConUnIdYNoExisteUnaCategoriaConEseIdDebeDevolverStatusNotFound() throws Exception {
        // Given
        given(servicio.getCategoriaById(1)).willReturn(Optional.empty());

        // When
        MockHttpServletResponse response = mockMvc.perform(get("/categorias/1")
                .accept(MediaType.APPLICATION_JSON))
                .andReturn()
                .getResponse();

        // Then
        assertEquals(HttpStatus.NOT_FOUND.value(), response.getStatus());
    }

    
}
