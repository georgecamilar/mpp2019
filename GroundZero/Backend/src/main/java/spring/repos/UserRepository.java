package spring.repos;

import spring.model.User;
import org.springframework.context.annotation.Bean;
import org.springframework.data.repository.CrudRepository;


public interface UserRepository extends CrudRepository<User, Integer> {
}
