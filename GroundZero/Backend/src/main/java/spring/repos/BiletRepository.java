package spring.repos;

import spring.model.Bilet;
import org.springframework.data.repository.CrudRepository;

public interface BiletRepository extends CrudRepository<Bilet, Integer> {
}
