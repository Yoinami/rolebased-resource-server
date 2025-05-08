package personal.yoinami.rolebasedresourceserver.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import personal.yoinami.rolebasedresourceserver.model.Product;
import personal.yoinami.rolebasedresourceserver.model.ShoppingCard;
import personal.yoinami.rolebasedresourceserver.service.ProductService;
import personal.yoinami.rolebasedresourceserver.service.ShoppingCardService;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("product")
public class ProductController {

    @Autowired
    ProductService productService;

    @Autowired
    ShoppingCardService shoppingCardService;

    @GetMapping("{id}")
    public String getSingleProductPage(
            @PathVariable int id, Model model, @RequestParam String message, @RequestParam String type) {

        Optional<Product> optionalProduct = productService.findById(id);
        if(optionalProduct.isPresent()) {
            model.addAttribute("message", message);
            model.addAttribute("type", type);
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

    @PostMapping("add/{id}")
    public String addProductToShoppingCard(@PathVariable int id) {
        try {
            shoppingCardService.addProductToShoppingCard(id);
        } catch (SQLIntegrityConstraintViolationException sqlIntegrityException) {
            return "redirect:/product/" + Integer.toString(id) + "?message=Item Already in the Cart&type=failed";
        }
        return "redirect:/product/" + Integer.toString(id) + "?message=Successfully Added&type=success";
    }
}
