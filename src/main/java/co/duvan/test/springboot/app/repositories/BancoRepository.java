package co.duvan.test.springboot.app.repositories;

import co.duvan.test.springboot.app.models.Banco;

import java.util.List;

public interface BancoRepository {

    Banco findById(Long id);

    List<Banco> findAll();

    void update(Banco banco);

}
