package tutorial.mintic.security.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import tutorial.mintic.security.models.User;

public interface UserRepository extends MongoRepository<User, String> {
}
