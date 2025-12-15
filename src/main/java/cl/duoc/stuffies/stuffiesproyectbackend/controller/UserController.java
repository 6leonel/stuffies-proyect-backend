package cl.duoc.stuffies.stuffiesproyectbackend.controller;

import cl.duoc.stuffies.stuffiesproyectbackend.entity.User;
import cl.duoc.stuffies.stuffiesproyectbackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "http://localhost:5173")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // ============================
    // LISTAR TODOS (SOLO ADMIN)
    // ============================
    @GetMapping
    public ResponseEntity<List<User>> getAll() {
        return ResponseEntity.ok(userRepository.findAll());
    }

    // ============================
    // PERFIL DEL USUARIO LOGEADO
    // ============================
    @GetMapping("/me")
    public ResponseEntity<?> getMe(Authentication authentication) {

        if (authentication == null) {
            return ResponseEntity.status(401).build();
        }

        String username = authentication.getName();

        return userRepository.findByUsername(username)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // ============================
    // ACTUALIZAR PERFIL LOGEADO
    // ============================
    // ============================
// ACTUALIZAR PERFIL LOGEADO
// ============================
    @PutMapping("/me")
    public ResponseEntity<?> updateMe(
            Authentication authentication,
            @RequestBody User updated
    ) {

        if (authentication == null) {
            return ResponseEntity.status(401).build();
        }

        String username = authentication.getName();

        return userRepository.findByUsername(username).map(user -> {

            // ✅ SOLO CAMPOS EDITABLES
            user.setNombre(updated.getNombre());
            user.setApellido(updated.getApellido());
            user.setDireccion(updated.getDireccion());

            // 🔐 Password opcional
            if (updated.getPassword() != null && !updated.getPassword().isBlank()) {
                user.setPassword(passwordEncoder.encode(updated.getPassword()));
            }

            // ❌ NO TOCAR:
            // rut
            // email
            // username
            // role

            userRepository.save(user);
            return ResponseEntity.ok(user);

        }).orElse(ResponseEntity.notFound().build());
    }


    // ============================
    // OBTENER USUARIO POR ID (ADMIN)
    // ============================
    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        return userRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // ============================
    // ELIMINAR USUARIO (ADMIN)
    // ============================
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {

        if (!userRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        userRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }

    // ============================
    // EDITAR USUARIO POR ID (ADMIN)
    // ============================
    // ============================
// EDITAR USUARIO POR ID (ADMIN)
// ============================
    @PutMapping("/{id}")
    public ResponseEntity<?> updateByAdmin(
            @PathVariable Long id,
            @RequestBody User updated
    ) {

        return userRepository.findById(id).map(user -> {

            // ===== VALIDAR USERNAME =====
            if (
                    updated.getUsername() != null &&
                            !updated.getUsername().equals(user.getUsername()) &&
                            userRepository.existsByUsername(updated.getUsername())
            ) {
                return ResponseEntity
                        .badRequest()
                        .body("El nombre de usuario ya existe");
            }

            // ===== VALIDAR EMAIL =====
            if (
                    updated.getEmail() != null &&
                            !updated.getEmail().equals(user.getEmail()) &&
                            userRepository.existsByEmail(updated.getEmail())
            ) {
                return ResponseEntity
                        .badRequest()
                        .body("El correo ya está registrado");
            }

            // ===== CAMPOS EDITABLES =====
            user.setNombre(updated.getNombre());
            user.setApellido(updated.getApellido());
            user.setDireccion(updated.getDireccion());
            user.setEmail(updated.getEmail());
            user.setUsername(updated.getUsername());

            // ===== ROL =====
            if (updated.getRole() != null && !updated.getRole().isBlank()) {
                String role = updated.getRole().toUpperCase();
                if (!role.startsWith("ROLE_")) {
                    role = "ROLE_" + role;
                }
                user.setRole(role);
            }

            // ===== PASSWORD OPCIONAL =====
            if (updated.getPassword() != null && !updated.getPassword().isBlank()) {
                user.setPassword(passwordEncoder.encode(updated.getPassword()));
            }

            userRepository.save(user);
            return ResponseEntity.ok(user);

        }).orElse(ResponseEntity.notFound().build());
    }
}
