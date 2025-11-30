package cl.duoc.stuffies.stuffiesproyectbackend.repository;

import cl.duoc.stuffies.stuffiesproyectbackend.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
