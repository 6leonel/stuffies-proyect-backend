package cl.duoc.stuffies.stuffiesproyectbackend.config;

import cl.duoc.stuffies.stuffiesproyectbackend.entity.Product;
import cl.duoc.stuffies.stuffiesproyectbackend.repository.ProductRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DataLoader {

    @Autowired
    private ProductRepository productRepository;

    @PostConstruct
    public void init() {

        if (productRepository.count() > 0) return;

        Product p2 = new Product();
        p2.setNombre("Hoodie Boxy Fit White Dice V2");
        p2.setPrecio(39990);
        p2.setDescripcion("Poleron Boxy Fit White Dice V2.");
        p2.setCategoria("polerones");
        p2.setImageUrl("https://stuffiesconcept.com/cdn/shop/files/WhiteDice1.png?v=1753404231&width=600");
        p2.setTallas("S,M,L,XL");
        p2.setStock(10);
        p2.setActivo(true);
        productRepository.save(p2);

        Product p3 = new Product();
        p3.setNombre("Star Player Blue Team T-Shirt");
        p3.setPrecio(10990);
        p3.setDescripcion("La Star Player T-Shirt nace de la unión entre la nostalgia del fútbol clásico y la energía del streetwear actual.");
        p3.setCategoria("poleras");
        p3.setImageUrl("https://stuffiesconcept.com/cdn/shop/files/1_594f01e1-55e5-4516-b0af-d2befc1aa113.png?v=1748653006&width=600");
        p3.setTallas("M,L,XL");
        p3.setStock(13);
        p3.setActivo(true);
        productRepository.save(p3);

        Product p4 = new Product();
        p4.setNombre("Stella Chroma Zip Hoodie");
        p4.setPrecio(55990);
        p4.setDescripcion("Hoodie con cierre frontal y bolsillos.");
        p4.setCategoria("polerones");
        p4.setImageUrl("https://stuffiesconcept.com/cdn/shop/files/1_8ee3f1b2-2f8a-45ba-bb78-a2f4ba49c4d5.png?v=1756936574&width=600");
        p4.setTallas("S,M,L,XL");
        p4.setStock(10);
        p4.setActivo(true);
        productRepository.save(p4);

        Product p5 = new Product();
        p5.setNombre("Stella Boxy-Slim White Tee");
        p5.setPrecio(22990);
        p5.setDescripcion("Camiseta blanca corte boxy-slim.");
        p5.setCategoria("poleras");
        p5.setImageUrl("https://stuffiesconcept.com/cdn/shop/files/3_0f38dc89-f9f8-4998-be22-b2e0122e8816.png?v=1756936601&width=600");
        p5.setTallas("S,M,L,XL");
        p5.setStock(14);
        p5.setActivo(true);
        productRepository.save(p5);

        Product p6 = new Product();
        p6.setNombre("Stella Boxy-Slim Black Tee");
        p6.setPrecio(15990);
        p6.setDescripcion("Polera boxy-slim fit negra");
        p6.setCategoria("poleras");
        p6.setImageUrl("https://stuffiesconcept.com/cdn/shop/files/5.png?v=1756936590&width=493");
        p6.setTallas(null);
        p6.setStock(20);
        p6.setActivo(true);
        productRepository.save(p6);

        Product p7 = new Product();
        p7.setNombre("Hoodie Boxy Fit Black Dice V2");
        p7.setPrecio(32990);
        p7.setDescripcion("Poleron Boxy Fit White Dice V2.");
        p7.setCategoria("polerones");
        p7.setImageUrl("https://stuffiesconcept.com/cdn/shop/files/RedDice1.png?v=1753404319&width=600");
        p7.setTallas("S,M,L,XL");
        p7.setStock(1);
        p7.setActivo(true);
        productRepository.save(p7);

        Product p8 = new Product();
        p8.setNombre("Star Player Black Team T-Shirt");
        p8.setPrecio(37990);
        p8.setDescripcion("La Star Player T-Shirt nace de la unión entre la nostalgia del fútbol clásico y la energía del streetwear actual..");
        p8.setCategoria("poleras");
        p8.setImageUrl("https://stuffiesconcept.com/cdn/shop/files/3_f5bf3ad8-c122-436f-8eee-1483a3f383da.png?v=1748652948&width=600");
        p8.setTallas("S,M,L,XL");
        p8.setStock(8);
        p8.setActivo(true);
        productRepository.save(p8);

        Product p9 = new Product();
        p9.setNombre("Hoodie Boxy Fit Brown Dice V2");
        p9.setPrecio(35990);
        p9.setDescripcion("Poleron Boxy Fit Brown Dice V2.");
        p9.setCategoria("polerones");
        p9.setImageUrl("https://stuffiesconcept.com/cdn/shop/files/PinkDice1.png?v=1753404299&width=600");
        p9.setTallas("S,M,L,XL");
        p9.setStock(8);
        p9.setActivo(true);
        productRepository.save(p9);

        Product p10 = new Product();
        p10.setNombre("Pantalón Jeans Negro");
        p10.setPrecio(22990);
        p10.setDescripcion("Jeans negro con calce relaxed.");
        p10.setCategoria("pantalones");
        p10.setImageUrl("https://i.postimg.cc/85CnPzS6/920c48b5-ab8b-486d-8681-74fd494c0b6e.avif");
        p10.setTallas("38,40,42,44,46,48,50,52,54");
        p10.setStock(11);
        p10.setActivo(true);
        productRepository.save(p10);

        Product p11 = new Product();
        p11.setNombre("Pantalón Jogger Gris");
        p11.setPrecio(19990);
        p11.setDescripcion("Jogger gris, cintura elasticada y puño.");
        p11.setCategoria("pantalones");
        p11.setImageUrl("https://img.kwcdn.com/product/fancy/50c868f6-9264-465b-8e4f-01332ba99b8d.jpg?imageView2/2/w/800/q/70/format/avif");
        p11.setTallas("38,40,42,44,46,48,50,52,54");
        p11.setStock(6);
        p11.setActivo(true);
        productRepository.save(p11);

        Product p12 = new Product();
        p12.setNombre("Gorro Beanie Clásico");
        p12.setPrecio(9990);
        p12.setDescripcion("Beanie de punto, unisex, ideal para invierno.");
        p12.setCategoria("gorros");
        p12.setImageUrl("https://img.kwcdn.com/product/fancy/109264d1-93cb-4d8a-af2f-a2e0056f21dc.jpg?imageView2/2/w/800/q/70/format/avif");
        p12.setTallas(null);
        p12.setStock(11);
        p12.setActivo(true);
        productRepository.save(p12);
    }
}
