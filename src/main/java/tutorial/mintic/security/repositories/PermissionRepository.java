package tutorial.mintic.security.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import tutorial.mintic.security.models.Permission;

import java.util.List;

public interface PermissionRepository  extends MongoRepository<Permission, String> {
    List<Permission> findByUrl(String url);
    List<Permission> findByMethod(String method);
}
