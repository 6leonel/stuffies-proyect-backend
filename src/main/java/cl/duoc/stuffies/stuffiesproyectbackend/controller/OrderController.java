package cl.duoc.stuffies.stuffiesproyectbackend.controller;

import cl.duoc.stuffies.stuffiesproyectbackend.dto.OrderRequest;
import cl.duoc.stuffies.stuffiesproyectbackend.entity.Order;
import cl.duoc.stuffies.stuffiesproyectbackend.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/orders")
@CrossOrigin(origins = "http://localhost:5173")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping
    public ResponseEntity<Order> createOrder(@RequestBody OrderRequest request,
                                             Authentication authentication) {

        String username = authentication != null ? authentication.getName() : null;
        Order order = orderService.createOrder(username, request);
        return ResponseEntity.ok(order);
    }

    @GetMapping
    public List<Order> getAllOrders() {
        return orderService.findAll();
    }

    @GetMapping("/mine")
    public List<Order> getMyOrders(Authentication authentication) {
        String username = authentication.getName();
        return orderService.findByUsername(username);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Order> getOrder(@PathVariable Long id) {
        Optional<Order> opt = orderService.findById(id);
        return opt.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Long id) {
        orderService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
