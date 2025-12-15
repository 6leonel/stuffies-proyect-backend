package cl.duoc.stuffies.stuffiesproyectbackend.controller;

import cl.duoc.stuffies.stuffiesproyectbackend.security.AuthRequest;
import cl.duoc.stuffies.stuffiesproyectbackend.security.AuthResponse;
import cl.duoc.stuffies.stuffiesproyectbackend.security.RegisterRequest;
import cl.duoc.stuffies.stuffiesproyectbackend.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "http://localhost:5173")
public class AuthController {

    @Autowired
    private AuthService authService;

    // ============================
    // LOGIN
    // ============================
    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest request) {

        AuthResponse response = authService.login(request);
        return ResponseEntity.ok(response);
    }

    // ============================
    // REGISTRO
    // ============================
    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody RegisterRequest request) {

        if (request.getUsername() == null || request.getUsername().isBlank()) {
            return ResponseEntity.badRequest().build();
        }

        if (request.getPassword() == null || request.getPassword().isBlank()) {
            return ResponseEntity.badRequest().build();
        }

        if (request.getEmail() == null || request.getEmail().isBlank()) {
            return ResponseEntity.badRequest().build();
        }

        if (request.getRole() == null || request.getRole().isBlank()) {
            return ResponseEntity.badRequest().build();
        }

        // Normalizamos rol SOLO para validar
        String role = request.getRole().trim().toUpperCase();
        if (!role.equals("CLIENTE") && !role.equals("VENDEDOR") && !role.equals("ADMIN")) {
            return ResponseEntity.badRequest().build();
        }

        try {
            AuthResponse newUser = authService.register(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(newUser);

        } catch (ResponseStatusException e) {
            return ResponseEntity.status(e.getStatusCode()).build();
        }
    }
}
