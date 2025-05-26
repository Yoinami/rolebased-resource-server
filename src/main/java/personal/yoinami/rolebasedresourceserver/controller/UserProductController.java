package personal.yoinami.rolebasedresourceserver.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import personal.yoinami.rolebasedresourceserver.model.Product;
import personal.yoinami.rolebasedresourceserver.service.ProductService;
import personal.yoinami.rolebasedresourceserver.service.ShoppingCardService;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/user/product")
public class UserProductController {

    @Autowired
    ProductService productService;

    @Autowired
    ShoppingCardService shoppingCardService;

    @GetMapping("/{id}")
    public String getSingleProductPage(
            @PathVariable int id,
            Model model,
            @RequestParam(defaultValue = "") String message,
            @RequestParam(defaultValue = "") String type
    ) {

        Optional<Product> optionalProduct = productService.findById(id);
        if(optionalProduct.isPresent()) {
            if(message.isBlank() || type.isBlank()) {
                message = null;
                type = null;
            }
            model.addAttribute("message", message);
            model.addAttribute("type", type);
            model.addAttribute("product", optionalProduct.get());
            return "/html/user/one_product";
        }
        return "/html/login";
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
    public String addProductToShoppingCard(@PathVariable int id, HttpServletRequest request) {
        String referrer = request.getHeader("referer");
        String redirect_url = (referrer != null) ? referrer : "/product/" + Integer.toString(id);
        if(redirect_url.contains("?")) redirect_url = redirect_url.substring(0, redirect_url.indexOf("?"));
        redirect_url = "redirect:" + redirect_url.replaceFirst("https?://[^/]+", "");
        System.out.println(redirect_url.replaceFirst("https?://[^/]+", ""));

        try {
            shoppingCardService.addProductToShoppingCart(id);
        } catch (SQLIntegrityConstraintViolationException sqlIntegrityException) {
            return redirect_url + "?message=Item Already in the Cart&type=failed";
        }
        return redirect_url + "?message=Successfully Added&type=success";
    }
}
