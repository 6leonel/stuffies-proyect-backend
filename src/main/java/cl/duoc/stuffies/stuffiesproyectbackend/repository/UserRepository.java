package cl.duoc.stuffies.stuffiesproyectbackend.repository;

import cl.duoc.stuffies.stuffiesproyectbackend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    // =========================
    // LOGIN / TOKEN / PERFIL
    // =========================
    Optional<User> findByUsername(String username);

    // =========================
    // VALIDACIONES REGISTRO
    // =========================
    boolean existsByUsername(String username);

    boolean existsByEmail(String email);
}
