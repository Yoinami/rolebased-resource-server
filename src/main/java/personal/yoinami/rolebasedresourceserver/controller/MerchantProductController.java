package personal.yoinami.rolebasedresourceserver.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MerchantProductController {

    @GetMapping("all")
    public String getAllProductPage(Model model) {
        return "/html/merchant/merchant_products";
    }
}
