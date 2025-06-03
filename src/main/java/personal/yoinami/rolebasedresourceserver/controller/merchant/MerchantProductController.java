package personal.yoinami.rolebasedresourceserver.controller.merchant;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import personal.yoinami.rolebasedresourceserver.component.RefererRedirect;
import personal.yoinami.rolebasedresourceserver.dto.CreateProductDTO;
import personal.yoinami.rolebasedresourceserver.model.Product;
import personal.yoinami.rolebasedresourceserver.service.ProductService;

@Controller
@RequestMapping("/merchant/product")
public class MerchantProductController {

    @Autowired
    ProductService productService;

    @ResponseBody
    @GetMapping("bruh")
    public String testing(HttpServletRequest request) {
        return RefererRedirect.sendRedirectTo(request);
    }

    @PostMapping("/add")
    public String createProduct(@ModelAttribute CreateProductDTO product) {
        try {
            productService.save(product);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return "redirect:/merchant/dashboard?message=Success";
    }

    @DeleteMapping("/delete")
    public String deleteProduct(@RequestParam int id) {
        try {
            productService.delete(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return "redirect:/merchant/products?message=Success";
    }

    @PatchMapping("/edit")
    public String editProduct(@ModelAttribute Product product) {
        try {
            productService.edit(product);
            return "redirect:/merchant/products?message=Success";
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


}
