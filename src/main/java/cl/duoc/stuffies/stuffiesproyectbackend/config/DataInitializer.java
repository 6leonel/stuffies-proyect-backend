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
    public void run(String... args) throws Exception {

        // si ya existe, no volvemos a crear
        if (userRepository.existsByUsername("adminstuffies")) {
            return;
        }

        User admin = new User();
        admin.setUsername("adminstuffies");
        admin.setPassword(passwordEncoder.encode("1234"));
        admin.setRole("ROLE_ADMIN");  // un solo rol

        userRepository.save(admin);
    }
}