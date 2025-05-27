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
import personal.yoinami.rolebasedresourceserver.service.ProductService;

import java.util.List;

@Controller
@RequestMapping("/merchant/product")
public class MerchantProductController {

    @Autowired
    ProductService productService;

    @ResponseBody
    @GetMapping("bruh")
    public String testing(HttpServletRequest request) {
        return RefererRedirect.sendRedirectTo(request);
    }

    @ResponseBody
    @GetMapping("/add")
    public Product createProduct(@RequestBody Product product) {
        return productService.save(product);
    }
}
