package cl.duoc.stuffies.stuffiesproyectbackend.controller;

import cl.duoc.stuffies.stuffiesproyectbackend.entity.Product;
import cl.duoc.stuffies.stuffiesproyectbackend.service.ProductService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
@SecurityRequirement(name = "bearerAuth") // para Swagger
@CrossOrigin(origins = "http://localhost:5173") // React Vite
public class ProductController {

    private final ProductService productService;

    // GET /api/products  -> lista todos
    @GetMapping
    public List<Product> getAll() {
        return productService.findAll();
    }

    // GET /api/products/{id}
    @GetMapping("/{id}")
    public ResponseEntity<Product> getById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(productService.findById(id));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // POST /api/products  -> crear
    @PostMapping
    public ResponseEntity<Product> create(@RequestBody Product product) {
        Product created = productService.create(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    // PUT /api/products/{id} -> actualizar
    @PutMapping("/{id}")
    public ResponseEntity<Product> update(
            @PathVariable Long id,
            @RequestBody Product product
    ) {
        Product updated = productService.update(id, product);
        return ResponseEntity.ok(updated);
    }

    // DELETE /api/products/{id} -> borrar
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        productService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
