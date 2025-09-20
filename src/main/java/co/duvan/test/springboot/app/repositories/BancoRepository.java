package co.duvan.test.springboot.app.repositories;

import co.duvan.test.springboot.app.models.Banco;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BancoRepository extends CrudRepository<Banco, Long> {

//    Banco findById(Long id);
//
//    List<Banco> findAll();
//
//    void update(Banco banco);

}
