package cl.duoc.stuffies.stuffiesproyectbackend.security;

import cl.duoc.stuffies.stuffiesproyectbackend.entity.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.*;

@Service
public class JwtService {

    private final Key key;
    private final long expirationMs;

    public JwtService(
            @Value("${security.jwt.secret}") String secret,
            @Value("${security.jwt.expiration-ms:3600000}") long expirationMs
    ) {
        this.key = Keys.hmacShaKeyFor(secret.getBytes());
        this.expirationMs = expirationMs;
    }

    // ====================================
    // GENERAR TOKEN CON ROLES EN ARRAY
    // ====================================
    public String generateToken(User user) {

        Map<String, Object> claims = new HashMap<>();

        // ⚠ IMPORTANTE: almacenar roles como ARRAY
        List<String> roles = new ArrayList<>();
        roles.add("ROLE_" + user.getRole());  // ejemplo: ADMIN → ROLE_ADMIN

        claims.put("roles", roles);

        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + expirationMs);

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(user.getUsername())
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    // ====================================
    // EXTRAER USERNAME
    // ====================================
    public String extractUsername(String token) {
        return extractAllClaims(token).getSubject();
    }

    // ====================================
    // EXTRAER ROLES
    // ====================================
    public List<String> extractRoles(String token) {
        Claims claims = extractAllClaims(token);
        return claims.get("roles", List.class);
    }

    // ====================================
    // VALIDAR TOKEN
    // ====================================
    public boolean isTokenValid(String token, String username) {
        return extractUsername(token).equals(username) && !isTokenExpired(token);
    }

    private boolean isTokenExpired(String token) {
        return extractAllClaims(token).getExpiration().before(new Date());
    }

    // ====================================
    // OBTENER CLAIMS
    // ====================================
    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}
