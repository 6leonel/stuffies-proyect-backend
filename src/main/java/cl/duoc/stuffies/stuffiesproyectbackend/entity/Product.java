package cl.duoc.stuffies.stuffiesproyectbackend.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "products")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 150)
    private String nombre;

    @Column(nullable = false)
    private Integer precio;

    @Column(length = 500)
    private String descripcion;

    @Column(length = 100)
    private String categoria;

    @Column(length = 500)
    private String imageUrl;

    private boolean activo = true;
}
