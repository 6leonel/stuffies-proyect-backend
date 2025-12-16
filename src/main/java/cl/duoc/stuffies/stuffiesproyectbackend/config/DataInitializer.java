package cl.duoc.stuffies.stuffiesproyectbackend.config;

import cl.duoc.stuffies.stuffiesproyectbackend.entity.User;
import cl.duoc.stuffies.stuffiesproyectbackend.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public DataInitializer(UserRepository userRepository,
                           PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) {

        // Si ya existe, no crear nuevamente
        if (userRepository.existsByUsername("adminstuffies")) {
            return;
        }

        User admin = new User();
        admin.setUsername("adminstuffies");
        admin.setPassword(passwordEncoder.encode("1234"));
        admin.setRole("ROLE_ADMIN");

        // ✅ CAMPOS OBLIGATORIOS
        admin.setNombre("Administrador");
        admin.setApellido("Stuffies");
        admin.setEmail("admin@stuffies.com");
        admin.setDireccion("Sin direccion");
        admin.setRut("11111111-1");

        userRepository.save(admin);
    }
}
