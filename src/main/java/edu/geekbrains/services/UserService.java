package edu.geekbrains.services;

import edu.geekbrains.converters.UserConverter;
import edu.geekbrains.dto.UserDto;
import edu.geekbrains.entities.User;
import edu.geekbrains.repositories.RoleRepository;
import edu.geekbrains.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserConverter userConverter;
    private final RoleRepository roleRepository;


    public Optional<User> findByUsername(String username) {
        return userRepository.findByUserName(username);
    }


    public void saveNewUser(UserDto newUserDto) {
        User newUser = userConverter.dtoToEntity(newUserDto);
        newUser.setRoles(Arrays.asList(roleRepository.findByName("USER")));
        userRepository.save(newUser);
    }
}