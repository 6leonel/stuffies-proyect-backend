package cl.duoc.stuffies.stuffiesproyectbackend.service;

import cl.duoc.stuffies.stuffiesproyectbackend.dto.OrderRequest;
import cl.duoc.stuffies.stuffiesproyectbackend.entity.Order;
import cl.duoc.stuffies.stuffiesproyectbackend.entity.OrderItem;
import cl.duoc.stuffies.stuffiesproyectbackend.entity.Product;
import cl.duoc.stuffies.stuffiesproyectbackend.entity.ProductVariant;
import cl.duoc.stuffies.stuffiesproyectbackend.repository.OrderRepository;
import cl.duoc.stuffies.stuffiesproyectbackend.repository.ProductRepository;
import cl.duoc.stuffies.stuffiesproyectbackend.repository.ProductVariantRepository;
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
    private ProductVariantRepository productVariantRepository;

    @Autowired
    private UserRepository userRepository;

    // =========================
    // CREAR ORDEN (Checkout)
    // =========================
    public Order createOrder(String username, OrderRequest request) {

        System.out.println("==== CREANDO ORDEN ====");

        if (request == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El cuerpo de la orden está vacío");
        }

        if (request.getItems() == null || request.getItems().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "La orden no tiene items");
        }

        Order order = new Order();

        // Usuario autenticado
        if (username != null && !username.isBlank()) {
            userRepository.findByUsername(username)
                    .ifPresent(order::setUser);
        }

        // Datos del cliente
        if (request.getCliente() != null) {
            order.setClienteNombre(request.getCliente().getNombre());
            order.setClienteEmail(request.getCliente().getEmail());
            order.setClienteDireccion(request.getCliente().getDireccion());
            order.setClienteTelefono(request.getCliente().getTelefono());
        }

        // Estado y medio de pago
        order.setEstado(
                request.getEstado() != null && !request.getEstado().isBlank()
                        ? request.getEstado()
                        : "PAGADO"
        );
        order.setMedioPago(
                request.getMedioPago() != null && !request.getMedioPago().isBlank()
                        ? request.getMedioPago()
                        : "WEB"
        );

        // Fecha de creación
        order.setFechaCreacion(LocalDateTime.now());

        // =========================
        // ITEMS + CÁLCULO TOTAL
        // =========================
        BigDecimal total = BigDecimal.ZERO;

        for (OrderRequest.ItemDTO itemReq : request.getItems()) {

            if (itemReq.getProductId() == null) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Item sin productId");
            }

            Product product = productRepository.findById(itemReq.getProductId())
                    .orElseThrow(() ->
                            new ResponseStatusException(
                                    HttpStatus.BAD_REQUEST,
                                    "Producto no encontrado: " + itemReq.getProductId()
                            ));

            OrderItem item = new OrderItem();

            // cantidad
            int cantidad = (itemReq.getCantidad() != null) ? itemReq.getCantidad() : 1;
            item.setCantidad(cantidad);

            // precio unitario
            int priceUnit = (itemReq.getPrecio() != null)
                    ? itemReq.getPrecio()
                    : product.getPrecio();
            item.setPrecio(priceUnit);

            // talla
            String talla = itemReq.getTalla();
            item.setTalla(talla);

            // ======= STOCK: variante por talla o producto completo =======
            ProductVariant variant = null;
            if (talla != null && !talla.isBlank()) {
                variant = productVariantRepository
                        .findByProductIdAndTalla(product.getId(), talla)
                        .orElse(null);
            }

            if (variant != null) {
                // Verificamos stock de la variante
                if (variant.getStock() < cantidad) {
                    throw new ResponseStatusException(
                            HttpStatus.BAD_REQUEST,
                            "Stock insuficiente para " + product.getNombre() +
                                    " talla " + talla
                    );
                }

                // Descontamos en la variante
                variant.setStock(variant.getStock() - cantidad);
                productVariantRepository.save(variant);

                // 🔹 Recalcular y guardar stock total del producto
                actualizarStockTotalProducto(product);

            } else {
                // Fallback: stock total en products (por si algún producto no usa variantes)
                Integer stockActual = product.getStock() != null ? product.getStock() : 0;
                if (stockActual < cantidad) {
                    throw new ResponseStatusException(
                            HttpStatus.BAD_REQUEST,
                            "Stock insuficiente para " + product.getNombre()
                    );
                }
                product.setStock(stockActual - cantidad);
                productRepository.save(product);
            }

            // Relación orden <-> item
            item.setProduct(product);
            item.setOrder(order);
            order.addItem(item);

            // Subtotal
            BigDecimal subtotal = BigDecimal.valueOf((long) priceUnit * cantidad);
            total = total.add(subtotal);
        }

        order.setTotal(total.intValue());
        Order saved = orderRepository.save(order);

        System.out.println("Orden guardada con ID = " + saved.getId());
        System.out.println("==== ORDEN CREADA OK ====");

        return saved;
    }

    // =========================
    // Helper: actualizar stock total en products
    // =========================
    private void actualizarStockTotalProducto(Product product) {
        List<ProductVariant> variants = productVariantRepository.findByProductId(product.getId());

        int total = variants.stream()
                .map(v -> v.getStock() == null ? 0 : v.getStock())
                .reduce(0, Integer::sum);

        product.setStock(total);
        productRepository.save(product);
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

    public void delete(Long id) {
        orderRepository.deleteById(id);
    }
}
