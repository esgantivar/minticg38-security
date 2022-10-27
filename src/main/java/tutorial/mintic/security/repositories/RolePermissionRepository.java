package tutorial.mintic.security.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import tutorial.mintic.security.models.Permission;
import tutorial.mintic.security.models.Role;
import tutorial.mintic.security.models.RolePermission;

import java.util.List;

public interface RolePermissionRepository extends MongoRepository<RolePermission, String> {
    List<RolePermission> findByRole(Role role);
    List<RolePermission> findByPermission(Permission permission);
}
