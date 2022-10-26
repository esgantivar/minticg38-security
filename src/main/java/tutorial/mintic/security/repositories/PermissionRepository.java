package tutorial.mintic.security.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import tutorial.mintic.security.models.Permission;

public interface PermissionRepository  extends MongoRepository<Permission, String> {
}
