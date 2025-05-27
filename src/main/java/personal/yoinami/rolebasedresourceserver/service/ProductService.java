package personal.yoinami.rolebasedresourceserver.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import personal.yoinami.rolebasedresourceserver.model.Product;
import personal.yoinami.rolebasedresourceserver.repository.ProductRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Optional<Product> findById(int productId) {
        return productRepository.findById(productId);
    }

    public List<Product> findByMerchantId(String merchantId) throws Exception {
        return productRepository.findByMerchantId(merchantId).orElseThrow(Exception::new);
    }

    public Product save(Product product) {
        return productRepository.save(product);
    }
}
