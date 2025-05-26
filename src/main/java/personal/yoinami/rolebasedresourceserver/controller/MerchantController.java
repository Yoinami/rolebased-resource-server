package personal.yoinami.rolebasedresourceserver.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/merchant")
public class MerchantController {

    @GetMapping("/dashboard")
    public String viewHome(Model model) {
        return "/html/merchant/merchant_dashboard";
    }

    @GetMapping("products")
    public String getAllProductPage(Model model) {
        return "/html/merchant/merchant_products";
    }
}
