package cl.duoc.stuffies.stuffiesproyectbackend.service;

import cl.duoc.stuffies.stuffiesproyectbackend.dto.ProductVariantDTO;
import cl.duoc.stuffies.stuffiesproyectbackend.entity.Product;
import cl.duoc.stuffies.stuffiesproyectbackend.entity.ProductVariant;
import cl.duoc.stuffies.stuffiesproyectbackend.repository.ProductRepository;
import cl.duoc.stuffies.stuffiesproyectbackend.repository.ProductVariantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductVariantService {

    @Autowired
    private ProductVariantRepository productVariantRepository;

    @Autowired
    private ProductRepository productRepository;

    // Obtener todas las variantes de un producto
    public List<ProductVariantDTO> getByProduct(Long productId) {
        List<ProductVariant> list = productVariantRepository.findByProductId(productId);

        return list.stream()
                .map(v -> new ProductVariantDTO(v.getId(), v.getTalla(), v.getStock()))
                .collect(Collectors.toList());
    }

    // Reemplazar completamente las variantes de un producto y actualizar stock total
    @Transactional
    public List<ProductVariantDTO> replaceForProduct(Long productId, List<ProductVariantDTO> dtos) {

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Producto no encontrado"
                ));

        // Borramos variantes actuales
        List<ProductVariant> actuales = productVariantRepository.findByProductId(productId);
        productVariantRepository.deleteAll(actuales);

        int totalStock = 0;

        for (ProductVariantDTO dto : dtos) {
            if (dto.getTalla() == null || dto.getTalla().trim().isEmpty()) {
                continue; // saltamos filas vacías
            }

            int stock = dto.getStock() != null ? dto.getStock() : 0;
            totalStock += stock;

            ProductVariant v = new ProductVariant();
            v.setProduct(product);
            v.setTalla(dto.getTalla().trim());
            v.setStock(stock);

            productVariantRepository.save(v);
        }

        // Actualizamos el stock total del producto
        product.setStock(totalStock);
        productRepository.save(product);

        return getByProduct(productId);
    }
}
