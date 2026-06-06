package com.styleshop.controller;

import com.styleshop.dto.LoginRequest;
import com.styleshop.dto.LoginResponse;
import com.styleshop.model.Usuario;
import com.styleshop.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {
        LoginResponse response = authService.login(request);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/registro")
    public ResponseEntity<Usuario> registro(@RequestBody Usuario nuevoUsuario) {
        Usuario usuario = authService.registro(nuevoUsuario);
        return ResponseEntity.ok(usuario);
    }
}