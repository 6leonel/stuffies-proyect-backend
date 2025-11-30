package cl.duoc.stuffies.stuffiesproyectbackend.config;

import cl.duoc.stuffies.stuffiesproyectbackend.entity.User;
import cl.duoc.stuffies.stuffiesproyectbackend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

@Configuration
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        if (!userRepository.existsByUsername("adminstuffies")) {
            User admin = User.builder()
                    .username("adminstuffies")
                    .password(passwordEncoder.encode("1234"))
                    .roles(List.of("ROLE_ADMIN"))
                    .build();
            userRepository.save(admin);
        }
    }
}
