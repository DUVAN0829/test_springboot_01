package co.duvan.test.springboot.app.services;

import co.duvan.test.springboot.app.models.Banco;
import co.duvan.test.springboot.app.models.Cuenta;
import co.duvan.test.springboot.app.repositories.BancoRepository;
import co.duvan.test.springboot.app.repositories.CuentaRepository;

import java.math.BigDecimal;

public class CuentaServicesImpl implements CuentaService{

    //* Vars
    private CuentaRepository cuentaRepository;
    private BancoRepository bancoRepository;

    //* Constructors
    public CuentaServicesImpl() {
    }

    public CuentaServicesImpl(CuentaRepository cuentaRepository, BancoRepository repository) {
        this.cuentaRepository = cuentaRepository;
        this.repository = repository;
    }

    //* Methods
    @Override
    public Cuenta findById(Long id) {
        return cuentaRepository.findById(id);
    }

    @Override
    public int revisarTotalTransferencia(Long bancoId) {

        Banco banco = bancoRepository.findById(bancoId);

        return banco.getTotalTransferencias();
    }

    @Override
    public BigDecimal revisarSaldo(Long cuentaId) {

        Cuenta cuenta = cuentaRepository.findById(cuentaId);

        return cuenta.getSaldo();
    }

    @Override
    public void transferir(Long bancoId, Long numCuentaOrigen, Long numCuentaDestino, BigDecimal monto) {

        //* Modificación Banco
        Banco banco = bancoRepository.findById(bancoId);
        int totalTransferencia = banco.getTotalTransferencias();
        banco.setTotalTransferencias(++totalTransferencia);
        bancoRepository.update(banco);

        //* Modificación cuenta origen
        Cuenta cuentaOrigen = cuentaRepository.findById(numCuentaOrigen);
        cuentaOrigen.debito(monto);
        cuentaRepository.update(cuentaOrigen);

        //* Modificación cuenta destino
        Cuenta cuentaDestino = cuentaRepository.findById(numCuentaDestino);
        cuentaDestino.credito(monto);
        cuentaRepository.update(cuentaDestino);

    }

}
