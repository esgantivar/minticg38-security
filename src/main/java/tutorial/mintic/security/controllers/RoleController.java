package tutorial.mintic.security.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import tutorial.mintic.security.models.Role;
import tutorial.mintic.security.repositories.RoleRepository;

import java.util.List;

@RestController
@RequestMapping("role")
public class RoleController {
    @Autowired
    private RoleRepository roleRepository;

    @GetMapping("")
    public List<Role> getAll() {
        return this.roleRepository.findAll();
    }

    @PostMapping
    public Role create(@RequestBody Role data) {
        return this.roleRepository.save(data);
    }

    @PutMapping("{id}")
    public Role update(@PathVariable String id, @RequestBody Role data) {
        Role currentRole =this.roleRepository.findById(id).orElse(null);
        if(currentRole == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "El rol no existe");
        }
        currentRole.setName(data.getName());
        currentRole.setDescription(data.getDescription());
        return this.roleRepository.save(currentRole);
    }

    @GetMapping("{id}")
    public Role getById(@PathVariable String id) {
        return this.roleRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "El rol no existe")
        );
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable String id) {
        this.roleRepository.findById(id).orElseThrow(()->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "El rol no existe")
        );
        this.roleRepository.deleteById(id);
    }
}
