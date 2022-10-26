package tutorial.mintic.security.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tutorial.mintic.security.models.Role;
import tutorial.mintic.security.repositories.RoleRepository;

import java.util.List;

@RestController
@RequestMapping("role")
public class RoleController {
    /**
     * Inyecta la dependencia del repositorio
     */
    @Autowired
    private RoleRepository roleRepository;

    @GetMapping("")
    public List<Role> getAll() {
        return this.roleRepository.findAll();
    }

    @PostMapping
    public Role create(@RequestBody Role data) {
        /*
        TODO: Cual va a ser nuestra regla de negocio para los roles
        No permitir que existan dos roles con el mismo nombre
        * */
        return this.roleRepository.save(data);
    }

    @PutMapping("{id}")
    public Role update(@PathVariable String id, @RequestBody Role data) {
        /**
         * TODO: pendiente por implementar
         */
    }

    @GetMapping("{id}")
    public Role getById(@PathVariable String id) {

    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable String id) {

    }
}
