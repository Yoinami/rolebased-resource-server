package personal.yoinami.rolebasedresourceserver.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import personal.yoinami.rolebasedresourceserver.service.ShoppingCardService;

import java.util.List;
import java.util.Map;

@Controller
public class ViewController {

    @Autowired
    ShoppingCardService shoppingCardService;

    @GetMapping("/")
    public String landingPage() {
        if(SecurityContextHolder.getContext().getAuthentication().getPrincipal().equals("anonymousUser")) return "/html/login";
        return "redirect:/user/dashboard";
    }

//    @SuppressWarnings("viewName")
//    @GetMapping("/{name}/dashboard")
//    public String showDashboard(@PathVariable String name) {
//
//        if(!name.equals("user") && !name.equals("merchant") && !name.equals("admin")) ResponseEntity.notFound();
//
//        return "html/" + name + "_dashboard";
//    }

    @ResponseBody
    @GetMapping("whoami")
    public Object whoami() {
        return ((OAuth2User) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
    }

    @ResponseBody
    @GetMapping("whoami2")
    public Object whoami2() {
        return SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    @ResponseBody
    @GetMapping("myroles")
    public Map<String,Object> myroles() {
        DefaultOidcUser defaultOidcUser = (DefaultOidcUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        return defaultOidcUser.getClaims();

    }

    @ResponseBody
    @GetMapping("cart")
    public List<?> shopping_cart() {
        DefaultOidcUser defaultOidcUser = (DefaultOidcUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        System.out.println(defaultOidcUser.getName());
        return shoppingCardService.getShoppingCard(defaultOidcUser.getName()).get();
    }
}
