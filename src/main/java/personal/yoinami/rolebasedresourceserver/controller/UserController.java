package personal.yoinami.rolebasedresourceserver.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import personal.yoinami.rolebasedresourceserver.model.Product;
import personal.yoinami.rolebasedresourceserver.model.ShoppingCard;
import personal.yoinami.rolebasedresourceserver.service.ProductService;
import personal.yoinami.rolebasedresourceserver.service.ShoppingCardService;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private ProductService productService;

    @Autowired
    private ShoppingCardService shoppingCardService;

    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        OAuth2User current_user = (OAuth2User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        List<ShoppingCard> shoppingCardList =  shoppingCardService.getShoppingCard(
                current_user.getName()).orElse(new ArrayList<>()
        );

        model.addAttribute("user", current_user);
        model.addAttribute("shopping_cart", shoppingCardList);

        return "html/user/user_dashboard";
    }

    @GetMapping("/store")
    public String store(Model model) {
        List<Product> listOfAllProducts = productService.getAllProducts();
        model.addAttribute("products", listOfAllProducts);
        return "html/user/e-commence";
    }

    @GetMapping("/order")
    public String order(Model model) {
        return "html/user/user-orders";
    }


}
