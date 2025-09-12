package co.duvan.test.springboot.app;

import co.duvan.test.springboot.app.models.Banco;
import co.duvan.test.springboot.app.models.Cuenta;

import java.math.BigDecimal;

public class Datos {

    public static Cuenta crearCuenta001() {
        return new Cuenta(1L, "Duván", new BigDecimal("1000"));
    }

    public static Cuenta crearCuenta002() {
        return new Cuenta(2L, "Katherin", new BigDecimal("2000"));
    }

    public static Banco crearBanco() {
        return new Banco(1l, "El banco financiero", 0);
    }

}
