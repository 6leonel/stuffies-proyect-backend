package cl.duoc.stuffies.stuffiesproyectbackend.dto;

public class ProductVariantDTO {

    private Long id;
    private String talla;
    private Integer stock;

    public ProductVariantDTO() {
    }

    public ProductVariantDTO(Long id, String talla, Integer stock) {
        this.id = id;
        this.talla = talla;
        this.stock = stock;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
}
