// src/main/java/cl/duoc/stuffies/stuffiesproyectbackend/security/AuthRequest.java
package cl.duoc.stuffies.stuffiesproyectbackend.security;

public class AuthRequest {

    private String username;
    private String password;

    public AuthRequest() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
