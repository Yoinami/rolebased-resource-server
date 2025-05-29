package personal.yoinami.rolebasedresourceserver.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import personal.yoinami.rolebasedresourceserver.component.RefererRedirect;
import personal.yoinami.rolebasedresourceserver.dto.CreateProductDTO;
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
    @PostMapping("/add")
    public Product createProduct(@ModelAttribute CreateProductDTO product) {
        return productService.save(product);
    }
}
