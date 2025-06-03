package personal.yoinami.rolebasedresourceserver.controller;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import personal.yoinami.rolebasedresourceserver.service.ShoppingCardService;

import java.io.File;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.*;

@Controller
public class ViewController {

    @Autowired
    ShoppingCardService shoppingCardService;

    // Testing Endpoints
    @GetMapping("/")
    public String landingPage() {
        return "/html/landingPage";
    }

    // Testing Endpoint
    @ResponseBody
    @GetMapping("whoami")
    public Object whoami() {
        return ((OAuth2User) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
    }

    // Testing Endpoint
    @ResponseBody
    @GetMapping("whoami2")
    public Object whoami2() {
        return SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    @ResponseBody
    @GetMapping("me")
    public Object me() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        HashMap<String, String> hm = new HashMap<>();
        hm.put("name", auth.getName());
        hm.put("principle", auth.getPrincipal().toString());
        hm.put("authorities", auth.getAuthorities().toString());
        return hm;
    }

    // Testing Endpoint
    @ResponseBody
    @GetMapping("myroles")
    public Map<String,Object> myroles() {
        DefaultOidcUser defaultOidcUser = (DefaultOidcUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        var hello = SecurityContextHolder.getContext();
        var bruh = SecurityContextHolder.getContext().getAuthentication();
        Collection<SimpleGrantedAuthority> authorities = (Collection<SimpleGrantedAuthority>)    SecurityContextHolder.getContext().getAuthentication().getAuthorities();
        return defaultOidcUser.getClaims();
    }

    // Testing Endpoint
    @ResponseBody
    @GetMapping("cart")
    public List<?> shopping_cart() {
        DefaultOidcUser defaultOidcUser = (DefaultOidcUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        System.out.println(defaultOidcUser.getName());
        return shoppingCardService.getShoppingCard(defaultOidcUser.getName()).get();
    }

    @GetMapping("/utf32")
    public void serveUtf32(HttpServletResponse response) throws Exception {
        response.setContentType("text/html; charset=UTF-16");
        File f = new File("D:\\Code\\git\\rolebased-resource-server\\src\\main\\resources\\templates\\html\\login.html");
        Scanner sc = new Scanner(f);
        StringBuilder html_data = new StringBuilder();

        while(sc.hasNextLine()) {
            html_data.append(sc.nextLine());
        }
        //String html = "<!DOCTYPE html><html><head><meta charset='UTF-32'></head><body><h1>😊 Hello UTF-32</h1></body></html>";
        // Convert string to UTF-32 bytes
        // Add BOM for UTF-16LE (Little Endian)
        byte[] bom = new byte[] {(byte)0xFF, (byte)0xFE};
        byte[] utf16Bytes = html_data.toString().getBytes(StandardCharsets.UTF_16LE);

        OutputStream out = response.getOutputStream();
        out.write(bom);             // Write BOM first
        out.write(utf16Bytes);      // Then write content
        out.flush();
    }
}
