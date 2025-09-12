package co.duvan.test.springboot.app.services;

import co.duvan.test.springboot.app.models.Cuenta;

import java.math.BigDecimal;

public interface CuentaService {

    Cuenta findById(Long id);

    int revisarTotalTransferencia(Long bancoId);

    BigDecimal revisarSaldo(Long cuentaId);

    void transferir(Long bancoId, Long numCuentaOrigen, Long numCuentaDestino, BigDecimal monto);

}
