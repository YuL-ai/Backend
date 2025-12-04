package com.usuarios.Demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthResponse<T> {
    private String token;
    private String rol;
    private T user;
}
