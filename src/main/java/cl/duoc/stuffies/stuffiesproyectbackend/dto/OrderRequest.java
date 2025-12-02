package cl.duoc.stuffies.stuffiesproyectbackend.dto;

import java.util.List;

public class OrderRequest {

    private ClienteDTO cliente;
    private List<ItemDTO> items;
    private Integer total;
    private String estado;
    private String medioPago;

    // ========= GETTERS / SETTERS =========

    public ClienteDTO getCliente() {
        return cliente;
    }

    public void setCliente(ClienteDTO cliente) {
        this.cliente = cliente;
    }

    public List<ItemDTO> getItems() {
        return items;
    }

    public void setItems(List<ItemDTO> items) {
        this.items = items;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getMedioPago() {
        return medioPago;
    }

    public void setMedioPago(String medioPago) {
        this.medioPago = medioPago;
    }

    // ========= CLASE INTERNA: CLIENTE =========

    public static class ClienteDTO {
        private String nombre;
        private String email;
        private String direccion;
        private String telefono;

        public ClienteDTO() {}

        public String getNombre() {
            return nombre;
        }

        public void setNombre(String nombre) {
            this.nombre = nombre;
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

        public String getTelefono() {
            return telefono;
        }

        public void setTelefono(String telefono) {
            this.telefono = telefono;
        }
    }

    // ========= CLASE INTERNA: ITEM =========

    public static class ItemDTO {

        private Long productId;
        private String nombre;
        private String talla;
        private String color;
        private Integer cantidad;
        private Integer precio;
        private String imagen;

        public ItemDTO() {}

        public Long getProductId() {
            return productId;
        }

        public void setProductId(Long productId) {
            this.productId = productId;
        }

        public String getNombre() {
            return nombre;
        }

        public void setNombre(String nombre) {
            this.nombre = nombre;
        }

        public String getTalla() {
            return talla;
        }

        public void setTalla(String talla) {
            this.talla = talla;
        }

        public String getColor() {
            return color;
        }

        public void setColor(String color) {
            this.color = color;
        }

        public Integer getCantidad() {
            return cantidad;
        }

        public void setCantidad(Integer cantidad) {
            this.cantidad = cantidad;
        }

        public Integer getPrecio() {
            return precio;
        }

        // CORREGIDO — este es el setter REAL
        public void setPrecio(Integer precio) {
            this.precio = precio;
        }

        public String getImagen() {
            return imagen;
        }

        public void setImagen(String imagen) {
            this.imagen = imagen;
        }
    }
}
