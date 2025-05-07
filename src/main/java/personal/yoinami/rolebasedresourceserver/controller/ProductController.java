package personal.yoinami.rolebasedresourceserver.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import personal.yoinami.rolebasedresourceserver.model.Product;
import personal.yoinami.rolebasedresourceserver.service.ProductService;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("product")
public class ProductController {

    @Autowired
    ProductService productService;

    @GetMapping("{id}")
    public String getSingleProductPage(@PathVariable int id, Model model) {
        Optional<Product> optionalProduct = productService.findById(id);
        if(optionalProduct.isPresent()) {
            model.addAttribute("product", optionalProduct.get());
            return "/html/one_product";
        }
        return "/html/logins";
    }

    @ResponseBody
    @GetMapping("all")
    public List<Product> getAll() {
        return productService.getAllProducts();
    }

    @ResponseBody
    @GetMapping("{id}/detail")
    public Product getSingleProduct(@PathVariable int id) {
        Optional<Product> optionalProduct = productService.findById(id);
        return optionalProduct.orElse(new Product());
    }
}
