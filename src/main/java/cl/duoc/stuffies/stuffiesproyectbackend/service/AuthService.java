package cl.duoc.stuffies.stuffiesproyectbackend.service;

import cl.duoc.stuffies.stuffiesproyectbackend.entity.User;
import cl.duoc.stuffies.stuffiesproyectbackend.repository.UserRepository;
import cl.duoc.stuffies.stuffiesproyectbackend.security.AuthRequest;
import cl.duoc.stuffies.stuffiesproyectbackend.security.AuthResponse;
import cl.duoc.stuffies.stuffiesproyectbackend.security.JwtService;
import cl.duoc.stuffies.stuffiesproyectbackend.security.RegisterRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;

    // ==========================
    // LOGIN
    // ==========================
    public AuthResponse login(AuthRequest request) {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );

        User user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() ->
                        new ResponseStatusException(
                                HttpStatus.UNAUTHORIZED,
                                "Usuario no encontrado"
                        )
                );

        // 🔑 ROL EXACTO DESDE BD
        String role = user.getRole();

        if (role == null || role.isBlank()) {
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    "Usuario sin rol asignado"
            );
        }

        String token = jwtService.generateToken(user);

        AuthResponse resp = new AuthResponse();
        resp.setToken(token);
        resp.setUsername(user.getUsername());
        resp.setRole(role);

        return resp;
    }

    // ==========================
    // REGISTER
    // ==========================
    public AuthResponse register(RegisterRequest request) {

        if (userRepository.existsByUsername(request.getUsername())) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "El nombre de usuario ya existe"
            );
        }

        if (userRepository.existsByEmail(request.getEmail())) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "El correo ya está registrado"
            );
        }

        User user = new User();

        // Datos personales
        user.setRut(request.getRut());
        user.setNombre(request.getNombre());
        user.setApellido(request.getApellido());
        user.setEmail(request.getEmail());
        user.setDireccion(request.getDireccion());

        // Credenciales
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        // ==========================
        // ROL (OBLIGATORIO Y CONTROLADO)
        // ==========================
        String role = request.getRole();

        if (role == null || role.isBlank()) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "El rol es obligatorio"
            );
        }

        role = role.trim().toUpperCase();

        if (!role.startsWith("ROLE_")) {
            role = "ROLE_" + role;
        }

        if (
                !role.equals("ROLE_CLIENTE") &&
                        !role.equals("ROLE_VENDEDOR") &&
                        !role.equals("ROLE_ADMIN")
        ) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Rol inválido"
            );
        }

        user.setRole(role);

        // Guardar usuario
        userRepository.save(user);

        // Token DEL USUARIO CREADO
        String token = jwtService.generateToken(user);

        AuthResponse resp = new AuthResponse();
        resp.setToken(token);
        resp.setUsername(user.getUsername());
        resp.setRole(role);

        return resp;
    }
}
