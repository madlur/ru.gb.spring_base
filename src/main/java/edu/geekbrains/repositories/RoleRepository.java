package edu.geekbrains.repositories;

import edu.geekbrains.entities.Role;
import org.springframework.data.repository.CrudRepository;

public interface RoleRepository extends CrudRepository<Role, Long> {

    Role findByName(String user);
}
