package cl.duoc.stuffies.stuffiesproyectbackend.controller;

import cl.duoc.stuffies.stuffiesproyectbackend.security.AuthRequest;
import cl.duoc.stuffies.stuffiesproyectbackend.security.AuthResponse;
import cl.duoc.stuffies.stuffiesproyectbackend.security.RegisterRequest;
import cl.duoc.stuffies.stuffiesproyectbackend.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<?> login(@RequestBody AuthRequest request) {
        try {
            AuthResponse response = authService.login(request);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity
                    .status(401)
                    .body("Usuario o contraseña incorrectos");
        }
    }

    // ============================
    // REGISTRO
    // ============================
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest request) {
        try {
            // Validaciones mínimas
            if (request.getUsername() == null || request.getUsername().isBlank()) {
                return ResponseEntity.badRequest().body("El usuario es obligatorio");
            }

            if (request.getPassword() == null || request.getPassword().isBlank()) {
                return ResponseEntity.badRequest().body("La contraseña es obligatoria");
            }

            if (request.getEmail() == null || request.getEmail().isBlank()) {
                return ResponseEntity.badRequest().body("El correo es obligatorio");
            }

            AuthResponse newUser = authService.register(request);
            return ResponseEntity.ok(newUser);

        } catch (Exception e) {
            return ResponseEntity
                    .badRequest()
                    .body("No se pudo registrar el usuario: " + e.getMessage());
        }
    }
}
