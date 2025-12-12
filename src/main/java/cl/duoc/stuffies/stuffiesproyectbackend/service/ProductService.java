package cl.duoc.stuffies.stuffiesproyectbackend.service;

import cl.duoc.stuffies.stuffiesproyectbackend.entity.Product;
import cl.duoc.stuffies.stuffiesproyectbackend.entity.ProductVariant;
import cl.duoc.stuffies.stuffiesproyectbackend.repository.ProductRepository;
import cl.duoc.stuffies.stuffiesproyectbackend.repository.ProductVariantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductVariantRepository productVariantRepository;

    public List<Product> findAll() {
        return productRepository.findAll();
    }

    public Product findById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado: " + id));
    }

    public Product create(Product product) {
        // aseguramos que sea uno nuevo
        product.setId(null);
        product.setActivo(true);

        // Guardamos el producto primero para obtener su ID
        Product createdProduct = productRepository.save(product);

        // Ahora creamos las variantes de producto (por talla)
        createProductVariants(createdProduct);

        return createdProduct;
    }

    public Product update(Long id, Product updated) {
        Product existing = findById(id);

        existing.setNombre(updated.getNombre());
        existing.setDescripcion(updated.getDescripcion());
        existing.setCategoria(updated.getCategoria());
        existing.setPrecio(updated.getPrecio());
        existing.setImageUrl(updated.getImageUrl());
        existing.setTallas(updated.getTallas());
        existing.setActivo(updated.isActivo());

        // Actualizar el producto
        Product updatedProduct = productRepository.save(existing);

        // Actualizar las variantes de producto
        createProductVariants(updatedProduct);

        return updatedProduct;
    }

    public void delete(Long id) {
        productRepository.deleteById(id);
    }

    // Método que gestiona la creación de variantes de producto (tallas y stock)
    private void createProductVariants(Product product) {
        // Obtener las tallas del producto (puede venir como "S,M,L,XL")
        if (product.getTallas() != null && !product.getTallas().isEmpty()) {
            String[] tallas = product.getTallas().split(",");

            for (String talla : tallas) {
                // Crear y asignar stock a las variantes
                ProductVariant variant = new ProductVariant();
                variant.setProduct(product);
                variant.setTalla(talla.trim()); // trim para eliminar espacios extra
                variant.setStock(generateRandomStock()); // Puedes modificar la lógica del stock si lo prefieres
                productVariantRepository.save(variant);
            }
        }
    }

    // Método para generar stock aleatorio entre 100 y 200 por variante
    private int generateRandomStock() {
        return (int) (Math.random() * (200 - 100 + 1)) + 100;
    }
}
