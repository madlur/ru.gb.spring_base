package edu.geekbrains.converters;

import edu.geekbrains.dto.UserDto;
import edu.geekbrains.entities.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserConverter {

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public User dtoToEntity(UserDto userDto) {

        return new User(userDto.getUserName(), bCryptPasswordEncoder.encode(userDto.getPassword()), userDto.getEmail());
    }


}
