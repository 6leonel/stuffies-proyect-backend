package cl.duoc.stuffies.stuffiesproyectbackend.entity;

import jakarta.persistence.*;

@Entity
@Table(
        name = "users",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "username"),
                @UniqueConstraint(columnNames = "email")
        }
)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // =========================
    // DATOS PERSONALES
    // =========================
    @Column(length = 20)
    private String rut;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private String apellido;

    @Column(nullable = false)
    private String email;

    private String direccion;

    // =========================
    // CREDENCIALES
    // =========================
    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    /**
     * ROLE_CLIENTE | ROLE_ADMIN | ROLE_VENDEDOR
     * ⚠️ SIEMPRE debe venir NORMALIZADO con ROLE_
     */
    @Column(nullable = false)
    private String role;

    // =========================
    // CONSTRUCTOR
    // =========================
    public User() {
    }

    // =========================
    // GETTERS / SETTERS
    // =========================
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRut() {
        return rut;
    }

    public void setRut(String rut) {
        this.rut = rut;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    // ⚠️ NUNCA EXPONGAS PASSWORD EN RESPUESTAS
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    // 🔴 ESTE MÉTODO ERA CLAVE PARA TU ERROR
    public String getRole() {
        return role;
    }

    // 🔴 NORMALIZA EL ROL SIEMPRE
    public void setRole(String role) {
        if (role == null || role.isBlank()) {
            this.role = "ROLE_CLIENTE";
        } else if (!role.startsWith("ROLE_")) {
            this.role = "ROLE_" + role.toUpperCase();
        } else {
            this.role = role.toUpperCase();
        }
    }
}
