package personal.yoinami.rolebasedresourceserver.controller;


import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("auth")
public class AuthController {


    @PostMapping("login")
    public HashMap<String, String> authorizeLoginPage() {
        HashMap<String, String> hello = new HashMap<>();
        hello.put("Authentication", "Endpoint Reached");
        return hello;
    }


    @GetMapping("token")
    public Map<String, Object> token() {
        OAuth2User user = ((OAuth2User) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        return user.getAttributes();
    }
}
