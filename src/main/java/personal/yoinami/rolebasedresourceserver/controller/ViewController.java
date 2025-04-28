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

    @GetMapping("dashboard")
    public String showDashboard() {
        return "html/user_dashboard.html";
    }

    @GetMapping("dashboard_testing")
    public ResponseEntity<String> showDashboardTesting() {
        return ResponseEntity.ok("Hello this is testing");
    }
}
