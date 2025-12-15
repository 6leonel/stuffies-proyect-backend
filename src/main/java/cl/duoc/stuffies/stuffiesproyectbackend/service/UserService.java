package cl.duoc.stuffies.stuffiesproyectbackend.service;

import cl.duoc.stuffies.stuffiesproyectbackend.entity.User;
import cl.duoc.stuffies.stuffiesproyectbackend.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository repo;

    public UserService(UserRepository repo) {
        this.repo = repo;
    }

    // ==========================
    // LISTAR TODOS (ADMIN)
    // ==========================
    public List<User> findAll() {
        return repo.findAll();
    }

    // ==========================
    // BUSCAR POR ID (ADMIN)
    // ==========================
    public Optional<User> findById(Long id) {
        return repo.findById(id);
    }

    // ==========================
    // BUSCAR POR USERNAME (CLAVE)
    // ==========================
    public Optional<User> findByUsername(String username) {
        return repo.findByUsername(username);
    }

    // ==========================
    // GUARDAR / ACTUALIZAR
    // ==========================
    public User save(User user) {
        return repo.save(user);
    }

    // ==========================
    // ELIMINAR (ADMIN)
    // ==========================
    public void delete(Long id) {
        if (repo.existsById(id)) {
            repo.deleteById(id);
        }
    }
}
