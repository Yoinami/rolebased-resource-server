package personal.yoinami.rolebasedresourceserver.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.authority.mapping.GrantedAuthoritiesMapper;
import org.springframework.security.oauth2.core.oidc.user.OidcUserAuthority;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Configuration
public class AuthorizationConfig {

    @Bean
    public GrantedAuthoritiesMapper userAuthoritiesMapper() {
        return (authorities) -> {
            Set<GrantedAuthority> mappedAuthorities = new HashSet<>();

            for (GrantedAuthority authority : authorities) {
                if (authority instanceof OidcUserAuthority oidcUserAuthority) {
                    List<String> clientRoles = getStrings(oidcUserAuthority, mappedAuthorities);
                    if (clientRoles != null) {
                        clientRoles.forEach(role ->
                                mappedAuthorities.add(new SimpleGrantedAuthority("ROLE_" + role))
                        );
                    }

                    // Keep existing OIDC authority
                    mappedAuthorities.add(authority);
                } else {
                    mappedAuthorities.add(authority);
                }
            }

            return mappedAuthorities;
        };
    }

    private static List<String> getStrings(OidcUserAuthority oidcUserAuthority, Set<GrantedAuthority> mappedAuthorities) {
        Map<String, Object> attributes = oidcUserAuthority.getAttributes();

        // Get realm_role
        List<String> realmRoles = (List<String>) attributes.get("realm_role");
        if (realmRoles != null) {
            realmRoles.forEach(role ->
                    mappedAuthorities.add(new SimpleGrantedAuthority("ROLE_" + role))
            );
        }

        // Get client_token
        List<String> clientRoles = (List<String>) attributes.get("client_token");
        return clientRoles;
    }

}
