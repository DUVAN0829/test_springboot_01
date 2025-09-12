package co.duvan.test.springboot.app;

import co.duvan.test.springboot.app.models.Banco;
import co.duvan.test.springboot.app.models.Cuenta;

import java.math.BigDecimal;

public class Datos {

    public static final Cuenta CUENTA_001 = new Cuenta(1L, "Duván", new BigDecimal("1000"));
    public static final Cuenta CUENTA_002 = new Cuenta(2L, "Katherin", new BigDecimal("2000"));
    public static final Banco BANCO  = new Banco(1l, "El banco financiero", 0);

}
