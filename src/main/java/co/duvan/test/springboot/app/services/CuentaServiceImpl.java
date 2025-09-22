package co.duvan.test.springboot.app.services;

import co.duvan.test.springboot.app.models.Banco;
import co.duvan.test.springboot.app.models.Cuenta;
import co.duvan.test.springboot.app.repositories.BancoRepository;
import co.duvan.test.springboot.app.repositories.CuentaRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class CuentaServiceImpl implements CuentaService{

    //* Vars
    private final CuentaRepository cuentaRepository;
    private final BancoRepository bancoRepository;

    //* Constructors
    public CuentaServiceImpl(CuentaRepository cuentaRepository, BancoRepository repository) {
        this.cuentaRepository = cuentaRepository;
        this.bancoRepository = repository;
    }

    //* Methods
    @Override
    public List<Cuenta> findAll() {
        return (List<Cuenta>) cuentaRepository.findAll();
    }

    @Override
    public Cuenta findById(Long id) {
        return cuentaRepository.findById(id).orElseThrow();
    }

    @Override
    public int revisarTotalTransferencia(Long bancoId) {

        Banco banco = bancoRepository.findById(bancoId).orElseThrow();

        return banco.getTotalTransferencias();
    }

    @Override
    public BigDecimal revisarSaldo(Long cuentaId) {

        Cuenta cuenta = cuentaRepository.findById(cuentaId).orElseThrow();

        return cuenta.getSaldo();
    }

    @Override
    public void transferir(Long bancoId, Long numCuentaOrigen, Long numCuentaDestino, BigDecimal monto) {

        //* Modificación cuenta origen
        Cuenta cuentaOrigen = cuentaRepository.findById(numCuentaOrigen).orElseThrow();
        cuentaOrigen.debito(monto);
        cuentaRepository.save(cuentaOrigen);

        //* Modificación cuenta destino
        Cuenta cuentaDestino = cuentaRepository.findById(numCuentaDestino).orElseThrow();
        cuentaDestino.credito(monto);
        cuentaRepository.save(cuentaDestino);

        //* Modificación Banco
        Banco banco = bancoRepository.findById(bancoId).orElseThrow();
        int totalTransferencia = banco.getTotalTransferencias();
        banco.setTotalTransferencias(++totalTransferencia);
        bancoRepository.save(banco);

    }

}
