package cl.duoc.stuffies.stuffiesproyectbackend.controller;

import cl.duoc.stuffies.stuffiesproyectbackend.dto.ProductVariantDTO;
import cl.duoc.stuffies.stuffiesproyectbackend.service.ProductVariantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products/{productId}/variants")
@CrossOrigin(origins = "http://localhost:5173")
public class ProductVariantController {

    @Autowired
    private ProductVariantService productVariantService;

    // GET /api/products/{id}/variants
    @GetMapping
    public List<ProductVariantDTO> getVariants(@PathVariable("productId") Long productId) {
        return productVariantService.getByProduct(productId);
    }

    // PUT /api/products/{id}/variants
    @PutMapping
    public List<ProductVariantDTO> updateVariants(
            @PathVariable("productId") Long productId,
            @RequestBody List<ProductVariantDTO> variants) {

        return productVariantService.replaceForProduct(productId, variants);
    }
}
