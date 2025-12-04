package com.usuarios.Demo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.usuarios.Demo.dto.APIResponse;
import com.usuarios.Demo.dto.AuthResponse;
import com.usuarios.Demo.dto.LoginRequest;
import com.usuarios.Demo.model.AdminModel;
import com.usuarios.Demo.model.UserModel;
import com.usuarios.Demo.service.AuthService;

import jakarta.persistence.EntityNotFoundException;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "*", allowCredentials = "false")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    /* Login ADMIN */
    @PostMapping("/login-admin")
    public ResponseEntity<APIResponse<AuthResponse<AdminModel>>> loginAdmin(
            @RequestBody LoginRequest request) {
        try {
            AuthResponse<AdminModel> resp = authService.loginAdmin(request);
            return ResponseEntity.ok(
                new APIResponse<>("OK", "Login admin correcto", resp)
            );
        } catch (EntityNotFoundException | IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(
                new APIResponse<>("ERROR", e.getMessage(), null)
            );
        }
    }

    /* Login USER */
    @PostMapping("/login-user")
    public ResponseEntity<APIResponse<AuthResponse<UserModel>>> loginUser(
            @RequestBody LoginRequest request) {
        try {
            AuthResponse<UserModel> resp = authService.loginUser(request);
            return ResponseEntity.ok(
                new APIResponse<>("OK", "Login usuario correcto", resp)
            );
        } catch (EntityNotFoundException | IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(
                new APIResponse<>("ERROR", e.getMessage(), null)
            );
        }
    }
}

