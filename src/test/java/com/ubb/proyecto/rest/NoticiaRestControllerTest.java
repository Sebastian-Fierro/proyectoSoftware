package com.ubb.proyecto.rest;

import com.ubb.proyecto.model.Noticia;
import com.ubb.proyecto.repository.RepositorioNoticia;
import com.ubb.proyecto.service.NoticiaService;
import com.ubb.proyecto.service.UsuarioService;
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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
public class NoticiaRestControllerTest {

    private JacksonTester<Noticia> jsonNoticia;
    private MockMvc mockMvc;

    @Mock
    private NoticiaService noticiaService;

    @Mock
    private UsuarioService usuarioService;

    @Mock
    private RepositorioNoticia repositorioNoticia;

    @InjectMocks
    private NoticiaRestControllerTest noticiaController;

    @BeforeEach
    void setup() {
        JacksonTester.initFields(this, new ObjectMapper());
        mockMvc = MockMvcBuilders.standaloneSetup(noticiaController).build();
    }

    @Test
    void siInvocoCrearNoticiaConPermisoDebeDevolverStatusOk() throws Exception {
        // Given
        Noticia noticia = new Noticia();
        noticia.setTitulo("Titulo de prueba");
        noticia.setContenido("Contenido de prueba");

        given(usuarioService.tienePermisoParaCrearNoticias(1)).willReturn(true);
        given(repositorioNoticia.save(noticia)).willReturn(noticia);

        // When
        MockHttpServletResponse response = mockMvc.perform(MockMvcRequestBuilders.post("/noticia/crear")
                        .param("usuarioId", "1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonNoticia.write(noticia).getJson()))
                .andReturn()
                .getResponse();

        // Then
        assertEquals(HttpStatus.OK.value(), response.getStatus());
        assertEquals("Noticia creada con éxito.", response.getContentAsString());
    }




}
