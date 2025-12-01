package cl.duoc.stuffies.stuffiesproyectbackend.dto;

public class UserDTO {
    public Long id;
    public String username;
    public String role;

    public UserDTO(Long id, String username, String role) {
        this.id = id;
        this.username = username;
        this.role = role;
    }
}
