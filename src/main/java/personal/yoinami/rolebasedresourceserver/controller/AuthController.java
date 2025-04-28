package personal.yoinami.rolebasedresourceserver.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
@RequestMapping("auth")
public class AuthController {


    @PostMapping("login")
    public HashMap<String, String> authorizeLoginPage() {
        HashMap<String, String> hello = new HashMap<>();
        hello.put("Authentication", "Endpoint Reached");
        return hello;
    }

    @GetMapping("login")
    public HashMap<String, String> authorizeLoginPageGet() {
        HashMap<String, String> hello = new HashMap<>();
        hello.put("Authentication", "Endpoint Reached");
        return hello;
    }


}
