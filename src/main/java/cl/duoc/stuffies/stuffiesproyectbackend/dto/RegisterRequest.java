package cl.duoc.stuffies.stuffiesproyectbackend.security;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {

    private String username;
    private String email;
    private String password;

    // 👇 NUEVO CAMPO PARA EL ROL
    // Puede venir "ADMIN", "USER", etc.
    private String role;
}
