package co.duvan.test.springboot.app;

import co.duvan.test.springboot.app.models.Cuenta;
import co.duvan.test.springboot.app.repositories.CuentaRepository;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.math.BigDecimal;
import java.util.List;
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

    @Test
    void testFindAll() {

        List<Cuenta> cuentas = (List<Cuenta>) cuentaRepository.findAll();
        assertFalse(cuentas.isEmpty());
        assertEquals(2, cuentas.size());

    }

    @Test
    void testSave() {

        //* Given
        Cuenta cuentaJon = new Cuenta(null, "Jhon", new BigDecimal("3000"));

        //* When
        Cuenta cuenta = cuentaRepository.save(cuentaJon);

        //* Then
        assertEquals("Jhon", cuenta.getPersona());
        assertEquals("3000", cuenta.getSaldo().toPlainString());
        //assertEquals(3L, cuenta1.getId());

    }

    @Test
    void testUpdate() {

        //* Given
        Cuenta cuentaJon = new Cuenta(null, "Jhon", new BigDecimal("3000"));

        //* When
        Cuenta cuenta = cuentaRepository.save(cuentaJon);

        //* Then
        assertEquals("Jhon", cuenta.getPersona());
        assertEquals("3000", cuenta.getSaldo().toPlainString());

        //* When
        cuenta.setSaldo(new BigDecimal("3800"));
        Cuenta cuentaActualizada = cuentaRepository.save(cuenta);

        //* Then
        assertEquals("Jhon", cuentaActualizada.getPersona());
        assertEquals("3800", cuentaActualizada.getSaldo().toPlainString());

    }

    @Test
    void testDelete() {

        Cuenta cuenta = cuentaRepository.findById(2L).orElseThrow();
        assertEquals("Katha", cuenta.getPersona());

        cuentaRepository.delete(cuenta);

        assertThrows(NoSuchElementException.class, () -> {
           //cuentaRepository.findByPersona("Katha").orElseThrow();
            cuentaRepository.findById(2L).orElseThrow();
        });

        List<Cuenta> lists = (List<Cuenta>) cuentaRepository.findAll();

        assertEquals(1,  lists.size());

    }

}













