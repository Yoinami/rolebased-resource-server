package personal.yoinami.rolebasedresourceserver.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import personal.yoinami.rolebasedresourceserver.model.Product;
import personal.yoinami.rolebasedresourceserver.service.ProductService;

import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private ProductService productService;

    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        model.addAttribute("user", (OAuth2User) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        return "html/user_dashboard";
    }

    @GetMapping("/store")
    public String store(Model model) {
        List<Product> listOfAllProducts = productService.getAllProducts();
        model.addAttribute("products", listOfAllProducts);
        return "html/e-commence";
    }

    @GetMapping("/order")
    public String order(Model model) {
        return "html/user-orders";
    }


}
