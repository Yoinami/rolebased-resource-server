package personal.yoinami.rolebasedresourceserver.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import personal.yoinami.rolebasedresourceserver.component.RefererRedirect;
import personal.yoinami.rolebasedresourceserver.model.Product;
import personal.yoinami.rolebasedresourceserver.repository.ProductRepository;

import java.util.List;

@Controller
@RequestMapping
public class MerchantProductController {

    @Autowired
    ProductRepository productRepository;

    public String createProduct(@RequestBody Product product) {
        productRepository.save(product);
        return "Success";
    }

    @ResponseBody
    @GetMapping("bruh")
    public String testing(HttpServletRequest request) {
        return RefererRedirect.sendRedirectTo(request);
    }

    @ResponseBody
    @GetMapping("all")
    public List<?> allProducts() {
        return productRepository.findAll();
    }
}
