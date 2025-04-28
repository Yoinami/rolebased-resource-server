package personal.yoinami.rolebasedresourceserver.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ViewController {

    @GetMapping("/")
    public ResponseEntity<String> landingPage() {
        return ResponseEntity.ok("Hello From the RolebasedResourceServerApplication");
    }

    @GetMapping("/login")
    public String loginPage() {
        return "login.html";
    }

    @GetMapping("/testLogin")
    public String testLoginPage() {
        return "login.html";
    }
}
