package personal.yoinami.rolebasedresourceserver.service;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import personal.yoinami.rolebasedresourceserver.component.AuthUtil;
import personal.yoinami.rolebasedresourceserver.dto.CreateProductDTO;
import personal.yoinami.rolebasedresourceserver.model.Product;
import personal.yoinami.rolebasedresourceserver.repository.ProductRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    AuthUtil authUtil;

    @Autowired
    ProductAttributeService productAttributeService;

    @Autowired
    AttributeService attributeService;

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Optional<Product> findById(int productId) {
        return productRepository.findById(productId);
    }

    public List<Product> findByMerchantId(String merchantId) throws Exception {
        return productRepository.findByMerchantId(merchantId).orElseThrow(Exception::new);
    }

    public Product save(CreateProductDTO productDTO) {
        Product product = new Product();
        product.setCategory(productDTO.getCategory());
        product.setName(productDTO.getName());
        product.setDetail(productDTO.getDetail());
        product.setProductSpecification(productDTO.getSpecificationList());
        product.setStockQuantity(productDTO.getStock());
        product.setMerchantId(authUtil.getAuthenticatedId());
        product.setPrice(Long.parseLong(productDTO.getPrice().toString()));
        product.setDescription("TEST PRODUCT");

        System.out.println(product);

        product.setProductSpecification(
                productAttributeService.regulatedProductAttribute(
                        product.getProductSpecification(),
                        product
                )
        );
        return productRepository.save(product);
    }

    public void delete(int product_id) {
        productRepository.deleteById(product_id);
    }

    public void edit(Product product) {
        System.out.println("----------------------------product="+ product.toString());
        productRepository.findById(product.getProductId())
                .map(i -> {
                    i.setName(product.getName());
                    i.setDetail(product.getDetail());
                    i.setPrice(product.getPrice());
                    i.setDescription(product.getDescription());
                    i.setCategory(product.getCategory());
                    i.setStockQuantity(product.getStockQuantity());
                    System.out.println(i);
                    return productRepository.save(i);
                }).orElseThrow(EntityNotFoundException::new);
    }
}
