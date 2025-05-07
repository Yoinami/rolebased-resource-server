package personal.yoinami.rolebasedresourceserver.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import personal.yoinami.rolebasedresourceserver.model.Product;
import personal.yoinami.rolebasedresourceserver.model.ProductAttribute;
import personal.yoinami.rolebasedresourceserver.repository.ProductAttributeRepository;
import personal.yoinami.rolebasedresourceserver.repository.ProductRepository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    ProductAttributeRepository productAttributeRepository;

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Optional<Product> findById(int product_id) {
        List<ProductAttribute> productAttributeList = productAttributeRepository.findAllByProductId(product_id);
        Optional<Product> productOptional = productRepository.findById(product_id);

        Product product = productOptional.orElse(new Product());
        product.setProductSpecification(productAttributeList);

        return productOptional;
    }
}
