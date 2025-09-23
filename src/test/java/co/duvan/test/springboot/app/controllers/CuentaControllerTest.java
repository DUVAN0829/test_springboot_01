package co.duvan.test.springboot.app.controllers;

import co.duvan.test.springboot.app.Datos;
import co.duvan.test.springboot.app.models.TransaccionDTO;
import co.duvan.test.springboot.app.services.CuentaService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hibernate.mapping.Any;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.assertj.MockMvcTester;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CuentaController.class)
class CuentaControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockitoBean
    private CuentaService service;

    ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        objectMapper = new ObjectMapper();
    }

    @Test
    void detalle() throws Exception {

        // Given
        when(service.findById(1L)).thenReturn(Datos.crearCuenta001().orElseThrow());

        // When
        mvc.perform(get("/api/cuenta/1").contentType(MediaType.APPLICATION_JSON))

                //Then
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.persona").value("Duván"))
                .andExpect(jsonPath("$.saldo").value("1000"));

        verify(service).findById(1L);

    }

    @Test
    void trasaccion() throws Exception {

        // Given
        TransaccionDTO dto = new TransaccionDTO();
        dto.setCuentaOrigenId(1L);
        dto.setCuentaDestinoId(2L);
        dto.setMonto(new BigDecimal("100"));
        dto.setBancoId(1L);

        // When
        mvc.perform(post("/api/cuenta/transferir").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(dto)))

                //Then
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.date").value(LocalDate.now().toString()))
                .andExpect(jsonPath("$.message").value("Transferencia realizada con exito"))
                .andExpect(jsonPath("$.transaccion.cuentaOrigenId").value(1L));

        verify(service).transferir(dto.getBancoId(), dto.getCuentaOrigenId(), dto.getCuentaDestinoId(), dto.getMonto());

    }
}