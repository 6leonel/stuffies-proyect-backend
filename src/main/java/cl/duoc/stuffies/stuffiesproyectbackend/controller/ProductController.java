package cl.duoc.stuffies.stuffiesproyectbackend.controller;

import cl.duoc.stuffies.stuffiesproyectbackend.entity.Product;
import cl.duoc.stuffies.stuffiesproyectbackend.repository.ProductRepository;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@SecurityRequirement(name = "bearerAuth") // Swagger pedirá JWT para estos endpoints
public class ProductController {

    private final ProductRepository productRepository;

    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    // GET /api/products  -> lista todos
    @GetMapping
    public List<Product> getAll() {
        return productRepository.findAll();
    }

    // GET /api/products/{id}  -> busca por id
    @GetMapping("/{id}")
    public ResponseEntity<Product> getById(@PathVariable Long id) {
        return productRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // POST /api/products  -> crea producto nuevo
    @PostMapping
    public ResponseEntity<Product> create(@RequestBody Product product) {
        Product saved = productRepository.save(product);
        return ResponseEntity.ok(saved);
    }

    // PUT /api/products/{id}  -> actualiza producto
    @PutMapping("/{id}")
    public ResponseEntity<Product> update(
            @PathVariable Long id,
            @RequestBody Product productRequest
    ) {
        return productRepository.findById(id)
                .map(p -> {
                    p.setNombre(productRequest.getNombre());
                    p.setDescripcion(productRequest.getDescripcion());
                    p.setPrecio(productRequest.getPrecio());
                    p.setCategoria(productRequest.getCategoria());
                    p.setImageUrl(productRequest.getImageUrl());
                    p.setActivo(productRequest.isActivo());
                    return ResponseEntity.ok(productRepository.save(p));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    // DELETE /api/products/{id}  -> elimina producto
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (!productRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        productRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
