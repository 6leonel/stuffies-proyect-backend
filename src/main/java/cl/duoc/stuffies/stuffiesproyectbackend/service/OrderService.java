package cl.duoc.stuffies.stuffiesproyectbackend.service;

import cl.duoc.stuffies.stuffiesproyectbackend.dto.OrderRequest;
import cl.duoc.stuffies.stuffiesproyectbackend.entity.Order;
import cl.duoc.stuffies.stuffiesproyectbackend.entity.OrderItem;
import cl.duoc.stuffies.stuffiesproyectbackend.entity.Product;
import cl.duoc.stuffies.stuffiesproyectbackend.repository.OrderRepository;
import cl.duoc.stuffies.stuffiesproyectbackend.repository.ProductRepository;
import cl.duoc.stuffies.stuffiesproyectbackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.time.LocalDateTime;
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

    public Order createOrder(String username, OrderRequest request) {

        if (request == null)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "La orden está vacía");

        if (request.getItems() == null || request.getItems().isEmpty())
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "La orden no tiene items");

        Order order = new Order();

        // Usuario autenticado
        if (username != null && !username.isBlank()) {
            userRepository.findByUsername(username).ifPresent(order::setUser);
        }

        // Datos del cliente
        if (request.getCliente() != null) {
            order.setClienteNombre(request.getCliente().getNombre());
            order.setClienteEmail(request.getCliente().getEmail());
            order.setClienteDireccion(request.getCliente().getDireccion());
            order.setClienteTelefono(request.getCliente().getTelefono());
        }

        // Estado
        order.setEstado(
                request.getEstado() != null ? request.getEstado() : "PAGADO"
        );

        order.setMedioPago(
                request.getMedioPago() != null ? request.getMedioPago() : "WEB"
        );

        order.setFechaCreacion(LocalDateTime.now());

        // Calcular total
        BigDecimal total = BigDecimal.ZERO;

        for (OrderRequest.ItemDTO dto : request.getItems()) {

            Product product = productRepository.findById(dto.getProductId())
                    .orElseThrow(() -> new ResponseStatusException(
                            HttpStatus.BAD_REQUEST, "Producto no existe: " + dto.getProductId()
                    ));

            OrderItem item = new OrderItem();
            item.setProduct(product);
            item.setPrecio(dto.getPrecio());
            item.setCantidad(dto.getCantidad());
            item.setTalla(dto.getTalla());
            item.setColor(dto.getColor());
            item.setImagen(dto.getImagen());
            item.setOrder(order);

            order.addItem(item);

            BigDecimal subtotal = BigDecimal.valueOf(item.getPrecio() * item.getCantidad());
            total = total.add(subtotal);
        }

        order.setTotal(total.intValue());

        return orderRepository.save(order);
    }

    public List<Order> findAll() { return orderRepository.findAll(); }

    public Optional<Order> findById(Long id) { return orderRepository.findById(id); }

    public List<Order> findByUsername(String username) {
        return orderRepository.findByUser_Username(username);
    }

    public void delete(Long id) { orderRepository.deleteById(id); }
}
