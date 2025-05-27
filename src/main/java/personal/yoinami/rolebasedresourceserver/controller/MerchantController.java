package personal.yoinami.rolebasedresourceserver.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import personal.yoinami.rolebasedresourceserver.component.AuthUtil;
import personal.yoinami.rolebasedresourceserver.service.ProductService;

@Controller
@RequestMapping("/merchant")
public class MerchantController {

    @Autowired
    ProductService productService;

    @Autowired
    AuthUtil authUtil;

    @GetMapping("/dashboard")
    public String viewHome(Model model) {
        return "/html/merchant/merchant_dashboard";
    }

    @GetMapping("/products")
    public String getAllProductPage(Model model) throws Exception {
        String merchantId = authUtil.getAuthenticatedId();
        model.addAttribute("products", productService.findByMerchantId(merchantId));
        return "/html/merchant/merchant_products";
    }

    @ResponseBody
    @GetMapping("/all")
    public Object getAll(Model model) throws Exception {
        String merchantId = authUtil.getAuthenticatedId();
        return productService.findByMerchantId(merchantId);
    }
}
