package cl.duoc.stuffies.stuffiesproyectbackend.repository;

import cl.duoc.stuffies.stuffiesproyectbackend.entity.ProductVariant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProductVariantRepository extends JpaRepository<ProductVariant, Long> {

    Optional<ProductVariant> findByProductIdAndTalla(Long productId, String talla);

    List<ProductVariant> findByProductId(Long productId);
}
