package cl.duoc.stuffies.stuffiesproyectbackend.controller;

import cl.duoc.stuffies.stuffiesproyectbackend.entity.User;
import cl.duoc.stuffies.stuffiesproyectbackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "http://localhost:5173")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    // ============================
    // LISTAR TODOS LOS USUARIOS
    // ============================
    @GetMapping
    public List<User> getAll() {
        return userRepository.findAll();
    }

    // ============================
    // OBTENER USUARIO POR ID
    // ============================
    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        return userRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // ============================
    // ELIMINAR USUARIO
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
    // EDITAR USUARIO
    // ============================
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody User updated) {

        return userRepository.findById(id).map(user -> {

            user.setRut(updated.getRut());
            user.setNombre(updated.getNombre());
            user.setApellido(updated.getApellido());
            user.setEmail(updated.getEmail());
            user.setDireccion(updated.getDireccion());
            user.setUsername(updated.getUsername());

            // si cambia password
            if (updated.getPassword() != null && !updated.getPassword().isBlank()) {
                user.setPassword(updated.getPassword());
            }

            user.setRole(updated.getRole());

            userRepository.save(user);
            return ResponseEntity.ok(user);

        }).orElse(ResponseEntity.notFound().build());
    }
}
