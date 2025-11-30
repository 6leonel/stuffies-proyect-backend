package cl.duoc.stuffies.stuffiesproyectbackend.service;

import cl.duoc.stuffies.stuffiesproyectbackend.dto.*;
import cl.duoc.stuffies.stuffiesproyectbackend.entity.User;
import cl.duoc.stuffies.stuffiesproyectbackend.repository.UserRepository;
import cl.duoc.stuffies.stuffiesproyectbackend.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthenticationManager authManager;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public AuthResponse login(AuthRequest request) {
        Authentication auth = authManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );

        User user = userRepository.findByUsername(request.getUsername())
                .orElseThrow();

        String token = jwtService.generateToken(user);

        return new AuthResponse(
                token,
                user.getUsername(),
                user.getRoles()
        );
    }

    public AuthResponse register(RegisterRequest request) {
        if (userRepository.existsByUsername(request.getUsername())) {
            throw new RuntimeException("El usuario ya existe");
        }

        User user = User.builder()
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .roles(List.of("ROLE_USER"))
                .build();

        userRepository.save(user);

        String token = jwtService.generateToken(user);

        return new AuthResponse(
                token,
                user.getUsername(),
                user.getRoles()
        );
    }
}
