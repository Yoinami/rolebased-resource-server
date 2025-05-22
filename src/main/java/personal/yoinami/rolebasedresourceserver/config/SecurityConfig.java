package personal.yoinami.rolebasedresourceserver.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.authority.mapping.GrantedAuthoritiesMapper;
import org.springframework.security.oauth2.core.oidc.user.OidcUserAuthority;
import org.springframework.security.web.SecurityFilterChain;

import java.util.*;
import java.util.logging.Logger;


@Configuration
@EnableWebSecurity(debug = true)
public class SecurityConfig {

    @Bean
    public SecurityFilterChain enableSecurity(
            HttpSecurity httpSecurity) throws Exception {

        httpSecurity.authorizeHttpRequests(req -> {

            req
                    .requestMatchers("/user/**").hasRole("buyer")
                    .requestMatchers("/*", "/css/**", "/js/**").permitAll()
                    .anyRequest().fullyAuthenticated();
        });

        httpSecurity.csrf(AbstractHttpConfigurer::disable);

        httpSecurity.oauth2Client(Customizer.withDefaults());
        httpSecurity.oauth2Login(config -> {
            config.tokenEndpoint(Customizer.withDefaults());
            config.userInfoEndpoint(endPoint -> endPoint.userAuthoritiesMapper(userAuthoritiesMapper()));
        })
                .sessionManagement(config -> config.sessionCreationPolicy(SessionCreationPolicy.ALWAYS));

        return httpSecurity.build();
    }

    @Bean
    public GrantedAuthoritiesMapper userAuthoritiesMapper() {
        return (authorities) -> {
            LinkedHashSet<GrantedAuthority> mappedAuthorities = new LinkedHashSet<>();

            for (GrantedAuthority authority : authorities) {
                if (authority instanceof OidcUserAuthority oidcUserAuthority) {
                    mappedAuthorities.add(authority);

                    setAuthorities(oidcUserAuthority, mappedAuthorities);
                } else {
                    mappedAuthorities.add(authority);
                }
            }

            Logger log = Logger.getLogger(AuthorizationConfig.class.getName());
            log.warning(mappedAuthorities.toString());
            return mappedAuthorities;
        };
    }

//    @Bean
//    public GrantedAuthoritiesMapper userAuthoritiesMapper() {
//        return (authorities) -> {
//            LinkedHashSet<GrantedAuthority> mappedAuthorities = new LinkedHashSet<>();
//
//            mappedAuthorities.add(new SimpleGrantedAuthority("ROLE_tester"));
//
//            Logger log = Logger.getLogger(AuthorizationConfig.class.getName());
//            log.warning(mappedAuthorities.toString());
//            return mappedAuthorities;
//        };
//    }

    private static void setAuthorities(OidcUserAuthority oidcUserAuthority, Collection<GrantedAuthority> mappedAuthorities) {
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
        if (clientRoles != null) {
            clientRoles.forEach(role ->
                    mappedAuthorities.add(new SimpleGrantedAuthority("ROLE_" + role))
            );
        }
    }
}
