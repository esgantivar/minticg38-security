package tutorial.mintic.security.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import tutorial.mintic.security.models.User;

import java.util.List;


public interface UserRepository extends MongoRepository<User, String> {
    List<User> findByUsername(String username);
}
