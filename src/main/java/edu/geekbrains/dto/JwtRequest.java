package edu.geekbrains.dto;

import lombok.Data;

@Data
public class JwtRequest {

    private String username;
    private String password;
    private String email;

}
