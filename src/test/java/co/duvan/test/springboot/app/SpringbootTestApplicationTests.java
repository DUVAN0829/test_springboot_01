package co.duvan.test.springboot.app;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import co.duvan.test.springboot.app.exceptions.DineroInsuficienteException;
import co.duvan.test.springboot.app.models.Banco;
import co.duvan.test.springboot.app.models.Cuenta;
import co.duvan.test.springboot.app.repositories.BancoRepository;
import co.duvan.test.springboot.app.repositories.CuentaRepository;
import co.duvan.test.springboot.app.services.CuentaService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.math.BigDecimal;

@SpringBootTest
class SpringbootTestApplicationTests {

    @MockitoBean
    CuentaRepository cuentaRepository;

    @MockitoBean
    BancoRepository bancoRepository;

    @Autowired
    CuentaService service;

    //@BeforeEach
    //void setUp() {
        //cuentaRepository = mock(CuentaRepository.class);
        //bancoRepository = mock(BancoRepository.class);
        //service = new CuentaServicesImpl(cuentaRepository, bancoRepository);
    //}

    @Test
    void contextLoads() {

        when(cuentaRepository.findById(1L)).thenReturn(Datos.crearCuenta001());
        when(cuentaRepository.findById(2L)).thenReturn(Datos.crearCuenta002());
        when(bancoRepository.findById(1L)).thenReturn(Datos.crearBanco());

        BigDecimal saldoOrigen = service.revisarSaldo(1L);
        BigDecimal saldoDestino = service.revisarSaldo(2L);

        assertEquals("1000", saldoOrigen.toPlainString());
        assertEquals("2000", saldoDestino.toPlainString());

        service.transferir(1l, 1L, 2L, new BigDecimal("100"));

        saldoOrigen = service.revisarSaldo(1L);
        saldoDestino = service.revisarSaldo(2L);
        int totalTransferencia = service.revisarTotalTransferencia(1L);

        assertEquals("900", saldoOrigen.toPlainString());
        assertEquals("2100", saldoDestino.toPlainString());
        assertEquals(1, totalTransferencia);

        verify(cuentaRepository, times(3)).findById(1L);
        verify(cuentaRepository, times(3)).findById(2L);
        verify(cuentaRepository, times(2)).save(any(Cuenta.class));

        verify(bancoRepository, times(2)).findById(1L);
        verify(bancoRepository).save(any(Banco.class));

        verify(cuentaRepository, never()).findAll();
        verify(cuentaRepository, times(6)).findById(anyLong());

    }

    @Test
    void contextLoads2() {

        //* Test I
        when(cuentaRepository.findById(1L)).thenReturn(Datos.crearCuenta001());
        when(cuentaRepository.findById(2L)).thenReturn(Datos.crearCuenta002());
        when(bancoRepository.findById(1L)).thenReturn(Datos.crearBanco());

        BigDecimal saldoOrigen = service.revisarSaldo(1L);
        BigDecimal saldoDestino = service.revisarSaldo(2L);
        assertEquals("1000", saldoOrigen.toPlainString());
        assertEquals("2000", saldoDestino.toPlainString());

        assertThrows(DineroInsuficienteException.class, () -> {
            service.transferir(1l, 1L, 2L, new BigDecimal("1200"));
        });

        //* Test II
        saldoOrigen = service.revisarSaldo(1L);
        saldoDestino = service.revisarSaldo(2L);
        int totalTransferencia = service.revisarTotalTransferencia(1L);

        assertEquals("1000", saldoOrigen.toPlainString());
        assertEquals("2000", saldoDestino.toPlainString());
        assertEquals(0, totalTransferencia);

        //* Verify
        verify(cuentaRepository, times(3)).findById(1L);
        verify(cuentaRepository, times(2)).findById(2L);
        verify(cuentaRepository, never()).save(any(Cuenta.class));

        verify(bancoRepository).findById(1L);
        verify(bancoRepository, never()).save(any(Banco.class));

        verify(cuentaRepository, never()).findAll();
        verify(cuentaRepository, times(5)).findById(anyLong());
    }

    @Test
    void contextLoads3() {

        when(cuentaRepository.findById(1L)).thenReturn(Datos.crearCuenta001());

        Cuenta cuenta1 = service.findById(1L);
        Cuenta cuenta2 = service.findById(1L);

        assertSame(cuenta1, cuenta2);
        assertEquals("Duván", cuenta1.getPersona());
        assertEquals("Duván", cuenta2.getPersona());

        verify(cuentaRepository, times(2)).findById(1L);

    }

}




















