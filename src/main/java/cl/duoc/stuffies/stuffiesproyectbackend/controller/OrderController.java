package cl.duoc.stuffies.stuffiesproyectbackend.controller;

import cl.duoc.stuffies.stuffiesproyectbackend.dto.OrderRequest;
import cl.duoc.stuffies.stuffiesproyectbackend.entity.Order;
import cl.duoc.stuffies.stuffiesproyectbackend.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
@CrossOrigin(origins = "*")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping
    public ResponseEntity<Order> createOrder(
            @RequestBody OrderRequest request,
            Authentication auth) {

        String username = auth != null ? auth.getName() : null;
        Order o = orderService.createOrder(username, request);

        return ResponseEntity.ok(o);
    }

    @GetMapping
    public List<Order> getAllOrders() {
        return orderService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Order> getOrder(@PathVariable Long id) {
        return orderService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Long id) {
        orderService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
