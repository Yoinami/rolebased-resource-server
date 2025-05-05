package personal.yoinami.rolebasedresourceserver.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class ViewController {

    @GetMapping("/")
    public String landingPage() {
        System.out.println(SecurityContextHolder.getContext().getAuthentication().getPrincipal());
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


    @GetMapping("whoami")
    public ResponseEntity<String> whoami() {
        OAuth2User user = ((OAuth2User) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        return ResponseEntity.ok(user.toString());
    }

    @GetMapping("whoami2")
    public ResponseEntity<String> whoami2() {
        return ResponseEntity.ok(SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());
    }

    @GetMapping("myroles")
    public ResponseEntity<String> myroles() {
        DefaultOidcUser defaultOidcUser = (DefaultOidcUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        return ResponseEntity.ok(defaultOidcUser.getClaims().get("client_token").toString());

    }
}
