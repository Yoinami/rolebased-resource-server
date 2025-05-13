package personal.yoinami.rolebasedresourceserver.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import personal.yoinami.rolebasedresourceserver.model.ShoppingCard;
import personal.yoinami.rolebasedresourceserver.service.OrderService;
import personal.yoinami.rolebasedresourceserver.service.ShoppingCardService;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("order")
public class OrderController {

    @Autowired
    ShoppingCardService shoppingCardService;
    @Autowired
    private OrderService orderService;

    @GetMapping("confirm")
    public String confirmPage(Model model) {

        OAuth2User current_user = (OAuth2User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Optional<List<ShoppingCard>> shoppingCardOptional = shoppingCardService.getShoppingCard(current_user.getName());

        model.addAttribute("shopping_cart", shoppingCardOptional.get());

        return "html/order_confirm";
    }

    @PostMapping("confirm")
    public ResponseEntity<?> confirmOrder() {
        OAuth2User current_user = (OAuth2User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        orderService.orderNow(current_user.getName());
        return ResponseEntity.ok().build();
    }
}
