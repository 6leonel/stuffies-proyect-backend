package cl.duoc.stuffies.stuffiesproyectbackend.repository;

import cl.duoc.stuffies.stuffiesproyectbackend.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByUser_Username(String username);
}
