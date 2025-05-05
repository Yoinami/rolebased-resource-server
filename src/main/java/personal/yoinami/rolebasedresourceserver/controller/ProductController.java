package personal.yoinami.rolebasedresourceserver.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import personal.yoinami.rolebasedresourceserver.model.Product;
import personal.yoinami.rolebasedresourceserver.service.ProductService;

import java.util.List;

@RestController()
@RequestMapping("products")
public class ProductController {

    @Autowired
    ProductService productService;

    @GetMapping("all")
    public List<Product> getAll() {
        return productService.getAllProducts();
    }
}
