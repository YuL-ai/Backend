package com.usuarios.Demo.service;

import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.usuarios.Demo.dto.AuthResponse;
import com.usuarios.Demo.dto.LoginRequest;
import com.usuarios.Demo.model.AdminModel;
import com.usuarios.Demo.model.UserModel;
import com.usuarios.Demo.repository.IAdminModelRepository;
import com.usuarios.Demo.repository.IUserModelRepository;
import com.usuarios.Demo.security.JwtUtil;

import jakarta.persistence.EntityNotFoundException;

@Service
public class AuthService {

    private final IAdminModelRepository adminRepo;
    private final IUserModelRepository userRepo;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public AuthService(
            IAdminModelRepository adminRepo,
            IUserModelRepository userRepo,
            PasswordEncoder passwordEncoder,
            JwtUtil jwtUtil
    ) {
        this.adminRepo = adminRepo;
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

    public AuthResponse<AdminModel> loginAdmin(LoginRequest request) {
        Optional<AdminModel> opt = adminRepo.findByEmail(request.getEmail());
        AdminModel admin = opt.orElseThrow(
                () -> new EntityNotFoundException("Admin no encontrado con ese correo")
        );

        if (!passwordEncoder.matches(request.getPassword(), admin.getPassword())) {
            throw new IllegalArgumentException("Credenciales inválidas");
        }

        String token = jwtUtil.generateToken(admin.getEmail(), "ADMIN");

        return new AuthResponse<>(token, "ADMIN", admin);
    }

    public AuthResponse<UserModel> loginUser(LoginRequest request) {
        Optional<UserModel> opt = userRepo.findByEmail(request.getEmail());
        UserModel user = opt.orElseThrow(
                () -> new EntityNotFoundException("Usuario no encontrado con ese correo")
        );

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new IllegalArgumentException("Credenciales inválidas");
        }

        String token = jwtUtil.generateToken(user.getEmail(), "USER");

        return new AuthResponse<>(token, "USER", user);
    }
}
