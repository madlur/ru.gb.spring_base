package edu.geekbrains.controllers;

import edu.geekbrains.dto.JwtRequest;
import edu.geekbrains.dto.JwtResponse;
import edu.geekbrains.dto.UserDto;
import edu.geekbrains.exceptions.AppError;
import edu.geekbrains.services.UserService;
import edu.geekbrains.services.UserServiceImpl;
import edu.geekbrains.utils.JwtTokenUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class LoginController {

    private final UserServiceImpl userServiceImpl;
    private final UserService userService;
    private final JwtTokenUtils jwtTokenUtils;
    private final AuthenticationManager authenticationManager;


    @PostMapping("/auth")
    public ResponseEntity<?> createAuthToken(@RequestBody JwtRequest authRequest) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
        } catch (BadCredentialsException ex) {
            return new ResponseEntity<>(new AppError("Incorrect username or password"), HttpStatus.UNAUTHORIZED);
        }

        UserDetails userDetails = userServiceImpl.loadUserByUsername(authRequest.getUsername());
        String token = jwtTokenUtils.generateToken(userDetails);
        return ResponseEntity.ok(new JwtResponse(token));
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerNewUser(@RequestBody JwtRequest authRequest) {
        if (userService.findByUsername(authRequest.getUsername()).isPresent()) {
            return new ResponseEntity<>(new AppError("Username is already exist"), HttpStatus.CONFLICT);
        }


        UserDto newUserDto = new UserDto(authRequest.getUsername(), authRequest.getPassword(), authRequest.getEmail());
        if(newUserDto.getEmail() == null) {
            newUserDto.setEmail("empty");
        }

        userService.saveNewUser(newUserDto);
        return ResponseEntity.ok(HttpStatus.CREATED);
    }


}
