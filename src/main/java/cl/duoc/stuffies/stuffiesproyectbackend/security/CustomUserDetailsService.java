package cl.duoc.stuffies.stuffiesproyectbackend.security;

import cl.duoc.stuffies.stuffiesproyectbackend.entity.User;
import cl.duoc.stuffies.stuffiesproyectbackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {

        User user = userRepository.findByUsername(username)
                .orElseThrow(() ->
                        new UsernameNotFoundException("Usuario no encontrado: " + username)
                );

        // Normalizamos rol con prefijo ROLE_
        String role = user.getRole();
        if (!role.startsWith("ROLE_")) {
            role = "ROLE_" + role;
        }

        return org.springframework.security.core.userdetails.User
                .withUsername(user.getUsername())
                .password(user.getPassword())
                .authorities(role)  // ahora siempre ROLE_ADMIN / ROLE_VENDEDOR / ROLE_CLIENTE
                .build();
    }

    public static class RegisterRequest {

        private String rut;
        private String nombre;
        private String apellido;
        private String email;
        private String direccion;

        private String username;
        private String password;
        private String role; // cliente / vendedor / admin

        public RegisterRequest() {}

        public String getRut() { return rut; }
        public void setRut(String rut) { this.rut = rut; }

        public String getNombre() { return nombre; }
        public void setNombre(String nombre) { this.nombre = nombre; }

        public String getApellido() { return apellido; }
        public void setApellido(String apellido) { this.apellido = apellido; }

        public String getEmail() { return email; }
        public void setEmail(String email) { this.email = email; }

        public String getDireccion() { return direccion; }
        public void setDireccion(String direccion) { this.direccion = direccion; }

        public String getUsername() { return username; }
        public void setUsername(String username) { this.username = username; }

        public String getPassword() { return password; }
        public void setPassword(String password) { this.password = password; }

        public String getRole() { return role; }
        public void setRole(String role) { this.role = role; }
    }
}
