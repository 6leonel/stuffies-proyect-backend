package cl.duoc.stuffies.stuffiesproyectbackend.config;

import cl.duoc.stuffies.stuffiesproyectbackend.security.JwtAuthFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    @Autowired
    private UserDetailsService customUserDetailsService;

    @Autowired
    private JwtAuthFilter jwtAuthFilter;

    // ==========================
    // SECURITY FILTER CHAIN
    // ==========================
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
                .csrf(csrf -> csrf.disable())
                .cors(cors -> {}) // usa el CorsConfigurationSource global
                .sessionManagement(session ->
                        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )

                .authorizeHttpRequests(auth -> auth

                        // ==========================
                        // SWAGGER
                        // ==========================
                        .requestMatchers(
                                "/v3/api-docs/**",
                                "/swagger-ui/**",
                                "/swagger-ui.html"
                        ).permitAll()

                        // ==========================
                        // AUTH
                        // ==========================
                        .requestMatchers("/auth/**").permitAll()

                        // ==========================
                        // PRODUCTOS (PÚBLICO)
                        // ==========================
                        .requestMatchers(HttpMethod.GET, "/api/products/**").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/products/*/variants").permitAll()

                        // ==========================
                        // ÓRDENES (COMPRAS SIN LOGIN)
                        // ==========================
                        .requestMatchers(HttpMethod.POST, "/api/orders").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/ordenes").permitAll()

                        // ✅ BOLETA DESDE CHECKOUT (FIX)
                        .requestMatchers(HttpMethod.GET, "/api/orders/*").permitAll()

                        // ==========================
                        // ÓRDENES PRIVADAS
                        // ==========================
                        .requestMatchers("/api/ordenes/mine").authenticated()
                        .requestMatchers(HttpMethod.GET, "/api/ordenes/**")
                        .hasAnyRole("ADMIN", "VENDEDOR")
                        .requestMatchers(HttpMethod.DELETE, "/api/ordenes/**")
                        .hasRole("ADMIN")

                        // ==========================
                        // USUARIO
                        // ==========================
                        .requestMatchers("/api/users/me").authenticated()
                        .requestMatchers("/api/users/**").hasRole("ADMIN")

                        // ==========================
                        // REPORTES
                        // ==========================
                        .requestMatchers("/api/reportes/**")
                        .hasAnyRole("ADMIN", "VENDEDOR")

                        // ==========================
                        // PRODUCTOS ADMIN
                        // ==========================
                        .requestMatchers(HttpMethod.DELETE, "/api/products/**")
                        .hasRole("ADMIN")

                        // ==========================
                        // TODO LO DEMÁS
                        // ==========================
                        .anyRequest().authenticated()
                )

                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    // ==========================
    // AUTH PROVIDER
    // ==========================
    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(customUserDetailsService);
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration config
    ) throws Exception {
        return config.getAuthenticationManager();
    }
}
