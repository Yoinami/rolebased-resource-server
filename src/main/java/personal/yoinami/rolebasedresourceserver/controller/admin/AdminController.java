package personal.yoinami.rolebasedresourceserver.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import personal.yoinami.rolebasedresourceserver.model.Product;
import personal.yoinami.rolebasedresourceserver.repository.ProductRepository;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    ProductRepository productRepository;

    @GetMapping("/dashboard")
    public String dashboard() {
        return "/html/admin/admin_dashboard";
    }

    @GetMapping("/products")
    public String getAllProduct(Model model) {
        List<Product> productList = productRepository.findAll();
        model.addAttribute("products", productList);
        return "/html/admin/admin_product";
    }

    @GetMapping("/merchant")
    public String getAllMerchant() {
        return "merchant";
    }
}
