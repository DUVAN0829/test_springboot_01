package co.duvan.test.springboot.app.repositories;

import co.duvan.test.springboot.app.models.Cuenta;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface CuentaRepository extends CrudRepository<Cuenta, Long> {

//    List<Cuenta> findAll();
//
//    Cuenta findById(Long id);
//
//    void update(Cuenta cuenta);

    @Query("select c from Cuenta c where c.persona=?1 and c.saldo=?2")
    Optional<Cuenta> findByPersona(String persona, BigDecimal saldo);

}
