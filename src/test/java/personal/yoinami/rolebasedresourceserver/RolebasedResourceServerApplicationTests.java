package personal.yoinami.rolebasedresourceserver;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import personal.yoinami.rolebasedresourceserver.config.SecurityConfig;
import personal.yoinami.rolebasedresourceserver.controller.ViewController;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ViewController.class) // include only this controller
@Import(SecurityConfig.class)
class RolebasedResourceServerApplicationTests {

    @Autowired
    private MockMvc mvc;

    @WithMockUser(authorities = "USER")
    @Test
    void endpointWhenUserAuthorityThenAuthorized() throws Exception {
        mvc.perform(get("/"))
                .andExpect(status().isOk());
    }

    @WithMockUser(authorities = "OTHER_ROLE")
    @Test
    void endpointWhenNotUserAuthorityThenForbidden() throws Exception {
        mvc.perform(get("/"))
                .andExpect(status().isForbidden());
    }

    @Test
    void anyWhenUnauthenticatedThenUnauthorized() throws Exception {
        mvc.perform(get("/"))
                .andExpect(status().isUnauthorized());
    }

}
