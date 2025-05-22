package personal.yoinami.rolebasedresourceserver.controller;


import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Map;

@RequestMapping("/auth")
@Controller
public class AuthController {

    @GetMapping("/login")
    public String authorizeLoginPage() {
        OAuth2AuthenticationToken oAuth2Token = null;
        try {
            oAuth2Token = ((OAuth2AuthenticationToken) SecurityContextHolder.getContext().getAuthentication());;
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
        if(oAuth2Token.getAuthorities().stream().anyMatch(auth -> auth.getAuthority().equals("ROLE_buyer"))) {
            return "redirect:/user/dashboard";
        }
        else if(oAuth2Token.getAuthorities().stream().anyMatch(auth -> auth.getAuthority().equals("ROLE_merchant"))) {
            return "redirect:/merchant/dashboard";
        }
        return "redirect:/";
    }

    @ResponseBody
    @GetMapping("token")
    public Map<String, Object> token() {
        OAuth2User user = ((OAuth2User) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        return user.getAttributes();
    }

    @ResponseBody
    @GetMapping("authorities")
    public Object authorities() {
        OAuth2User user = ((OAuth2User) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        return user.getAuthorities();
    }

    @ResponseBody
    @GetMapping("roles")
    public String roles() {
        System.out.println((Collection<SimpleGrantedAuthority>) SecurityContextHolder.getContext().getAuthentication().getAuthorities());

        return "watch console";
    }
}
