package co.duvan.test.springboot.app.repositories;

import co.duvan.test.springboot.app.models.Cuenta;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CuentaRepository extends CrudRepository<Cuenta, Long> {

//    List<Cuenta> findAll();
//
//    Cuenta findById(Long id);
//
//    void update(Cuenta cuenta);



}
