package cl.duoc.stuffies.stuffiesproyectbackend.service;

import cl.duoc.stuffies.stuffiesproyectbackend.dto.OrderRequest;
import cl.duoc.stuffies.stuffiesproyectbackend.entity.Order;
import cl.duoc.stuffies.stuffiesproyectbackend.entity.OrderItem;
import cl.duoc.stuffies.stuffiesproyectbackend.entity.Product;
import cl.duoc.stuffies.stuffiesproyectbackend.entity.User;
import cl.duoc.stuffies.stuffiesproyectbackend.repository.OrderRepository;
import cl.duoc.stuffies.stuffiesproyectbackend.repository.ProductRepository;
import cl.duoc.stuffies.stuffiesproyectbackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserRepository userRepository;

    // =========================
    // CREAR ORDEN (Checkout)
    // =========================
    public Order createOrder(String username, OrderRequest request) {

        Order order = new Order();

        // USUARIO AUTENTICADO (opcional)
        if (username != null) {
            userRepository.findByUsername(username)
                    .ifPresent(order::setUser);
        }

        // CLIENTE
        if (request.getCliente() != null) {
            order.setClienteNombre(request.getCliente().getNombre());
            order.setClienteEmail(request.getCliente().getEmail());
            order.setClienteDireccion(request.getCliente().getDireccion());
            order.setClienteTelefono(request.getCliente().getTelefono());
        }

        // ESTADO / MEDIO PAGO
        String estado = (request.getEstado() != null && !request.getEstado().isBlank())
                ? request.getEstado()
                : "PAGADO";

        String medioPago = (request.getMedioPago() != null && !request.getMedioPago().isBlank())
                ? request.getMedioPago()
                : "WEB";

        order.setEstado(estado);
        order.setMedioPago(medioPago);

        // =========================
        // ITEMS + CÁLCULO DE TOTAL
        // =========================
        BigDecimal total = BigDecimal.ZERO;

        if (request.getItems() != null) {
            for (OrderRequest.ItemDTO itemReq : request.getItems()) {

                // Buscar producto real
                Product product = productRepository.findById(itemReq.getProductId())
                        .orElseThrow(() ->
                                new RuntimeException("Producto no encontrado: " + itemReq.getProductId()));

                OrderItem item = new OrderItem();

                // Datos del producto
                item.setProduct(product);

                // Cantidad
                int cantidad = (itemReq.getCantidad() != null)
                        ? itemReq.getCantidad()
                        : 1;
                item.setCantidad(cantidad);

                // Precio unitario
                int priceUnit = (itemReq.getPrecio() != null)
                        ? itemReq.getPrecio()
                        : product.getPrecio();
                item.setPrecio(priceUnit);

                // Talla / Color / Imagen
                item.setTalla(itemReq.getTalla());
                item.setColor(itemReq.getColor());
                item.setImagen(itemReq.getImagen());

                // Relación bidireccional
                item.setOrder(order);
                order.addItem(item);

                // Subtotal
                BigDecimal subtotal = BigDecimal.valueOf((long) priceUnit * cantidad);
                total = total.add(subtotal);
            }
        }

        // Total final
        order.setTotal(total.intValue());

        // Guardar
        return orderRepository.save(order);
    }

    // =========================
    // MÉTODOS CRUD
    // =========================
    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    public Optional<Order> findById(Long id) {
        return orderRepository.findById(id);
    }

    public List<Order> findByUsername(String username) {
        return orderRepository.findByUser_Username(username);
    }

    public void delete(Long id) {
        orderRepository.deleteById(id);
    }
}
