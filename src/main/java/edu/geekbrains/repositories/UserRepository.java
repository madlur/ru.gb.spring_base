package edu.geekbrains.repositories;

import edu.geekbrains.entities.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {


    Optional<User> findByUserName(String userName);
}
