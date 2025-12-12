package cl.duoc.stuffies.stuffiesproyectbackend.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

@Entity
@Table(name = "product_variants")
public class ProductVariant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    @JsonIgnoreProperties("variants") // evita recursión al serializar
    private Product product;

    @Column(nullable = false)
    private String talla;

    @Column(nullable = false)
    private Integer stock;

    // ======= CONSTRUCTORES =======
    public ProductVariant() {
    }

    public ProductVariant(Long id, Product product, String talla, Integer stock) {
        this.id = id;
        this.product = product;
        this.talla = talla;
        this.stock = stock;
    }

    // ======= GETTERS y SETTERS =======

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public String getTalla() {
        return talla;
    }

    public void setTalla(String talla) {
        this.talla = talla;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    // Método para actualizar el stock de la variante
    public void actualizarStock(int cantidad) {
        this.stock -= cantidad;
    }
}
