package cl.duoc.stuffies.stuffiesproyectbackend.dto;

import java.util.List;

public class OrderRequest {

    private ClienteDTO cliente;
    private List<ItemDTO> items;
    private String estado;
    private String medioPago;

    public ClienteDTO getCliente() { return cliente; }
    public void setCliente(ClienteDTO cliente) { this.cliente = cliente; }

    public List<ItemDTO> getItems() { return items; }
    public void setItems(List<ItemDTO> items) { this.items = items; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }

    public String getMedioPago() { return medioPago; }
    public void setMedioPago(String medioPago) { this.medioPago = medioPago; }

    public static class ClienteDTO {
        private String nombre;
        private String email;
        private String direccion;
        private String telefono;

        public String getNombre() { return nombre; }
        public void setNombre(String nombre) { this.nombre = nombre; }

        public String getEmail() { return email; }
        public void setEmail(String email) { this.email = email; }

        public String getDireccion() { return direccion; }
        public void setDireccion(String direccion) { this.direccion = direccion; }

        public String getTelefono() { return telefono; }
        public void setTelefono(String telefono) { this.telefono = telefono; }
    }

    public static class ItemDTO {
        private Long productId;
        private Integer precio;
        private Integer cantidad;
        private String talla;
        private String color;
        private String imagen;

        public Long getProductId() { return productId; }
        public void setProductId(Long productId) { this.productId = productId; }

        public Integer getPrecio() { return precio; }
        public void setPrecio(Integer precio) { this.precio = precio; }

        public Integer getCantidad() { return cantidad; }
        public void setCantidad(Integer cantidad) { this.cantidad = cantidad; }

        public String getTalla() { return talla; }
        public void setTalla(String talla) { this.talla = talla; }

        public String getColor() { return color; }
        public void setColor(String color) { this.color = color; }

        public String getImagen() { return imagen; }
        public void setImagen(String imagen) { this.imagen = imagen; }
    }
}
