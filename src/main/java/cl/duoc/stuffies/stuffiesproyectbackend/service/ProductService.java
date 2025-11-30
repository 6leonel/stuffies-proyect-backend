package cl.duoc.stuffies.stuffiesproyectbackend.service;

import cl.duoc.stuffies.stuffiesproyectbackend.entity.Product;
import cl.duoc.stuffies.stuffiesproyectbackend.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public List<Product> findAll() {
        return productRepository.findAll();
    }

    public Product findById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));
    }

    public Product create(Product product) {
        product.setId(null);
        return productRepository.save(product);
    }

    public Product update(Long id, Product updated) {
        Product existing = findById(id);
        existing.setNombre(updated.getNombre());
        existing.setDescripcion(updated.getDescripcion());
        existing.setCategoria(updated.getCategoria());
        existing.setPrecio(updated.getPrecio());
        existing.setImageUrl(updated.getImageUrl());
        existing.setActivo(updated.isActivo());
        return productRepository.save(existing);
    }

    public void delete(Long id) {
        productRepository.deleteById(id);
    }
}
