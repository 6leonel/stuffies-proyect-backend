package cl.duoc.stuffies.stuffiesproyectbackend.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "products")
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

    // NUEVO: tallas como texto, por ejemplo "S,M,L,XL"
    @Column(length = 100)
    private String tallas;

    // NUEVO: stock total del producto
    @Column(nullable = false)
    private Integer stock = 0;

    // para activar/desactivar productos sin borrarlos
    @Column(nullable = false)
    private boolean activo = true;

    // ======= CONSTRUCTORES =======
    public Product() {
    }

    public Product(Long id,
                   String nombre,
                   Integer precio,
                   String descripcion,
                   String categoria,
                   String imageUrl,
                   String tallas,
                   Integer stock,
                   boolean activo) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
        this.descripcion = descripcion;
        this.categoria = categoria;
        this.imageUrl = imageUrl;
        this.tallas = tallas;
        this.stock = stock;
        this.activo = activo;
    }

    // ======= GETTERS y SETTERS =======

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getPrecio() {
        return precio;
    }

    public void setPrecio(Integer precio) {
        this.precio = precio;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getTallas() {
        return tallas;
    }

    public void setTallas(String tallas) {
        this.tallas = tallas;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }
}
