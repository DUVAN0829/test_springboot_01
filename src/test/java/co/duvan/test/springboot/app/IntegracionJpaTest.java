package co.duvan.test.springboot.app;

import co.duvan.test.springboot.app.models.Cuenta;
import co.duvan.test.springboot.app.repositories.CuentaRepository;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.NoSuchElementException;
import java.util.Optional;

@DataJpaTest
public class IntegracionJpaTest {

    @Autowired
    CuentaRepository cuentaRepository;

    @Test
    void testFindById() {

        Optional<Cuenta> cuenta = cuentaRepository.findById(1L);
        assertTrue(cuenta.isPresent());
        assertEquals("Duván", cuenta.get().getPersona());


    }

    @Test
    void testFindByPersona() {

        Optional<Cuenta> cuenta = cuentaRepository.findByPersona("Duván");

        assertTrue(cuenta.isPresent());
        assertEquals("Duván", cuenta.get().getPersona());
        assertEquals("1000.00", cuenta.orElseThrow().getSaldo().toPlainString());

    }

    @Test
    void testFindByPersonaThrowException() {

        Optional<Cuenta> cuenta = cuentaRepository.findByPersona("Duv");

        assertThrows(NoSuchElementException.class, cuenta::orElseThrow);

        assertFalse(cuenta.isPresent());

    }

}
















