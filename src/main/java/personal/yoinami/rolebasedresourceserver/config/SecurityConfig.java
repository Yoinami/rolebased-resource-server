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
            HttpSecurity httpSecurity,
            GrantedAuthoritiesMapper grantedAuthoritiesMapper) throws Exception {

        httpSecurity.authorizeHttpRequests(req -> {

            req
                  //  .requestMatchers("/user/**").hasRole("buyer")
                   // .requestMatchers("/*", "/css/**", "/js/**").permitAll()
                    .anyRequest().fullyAuthenticated();
        });

        httpSecurity.csrf(AbstractHttpConfigurer::disable);

        httpSecurity.oauth2Client(Customizer.withDefaults());
        httpSecurity.oauth2Login(config -> {
            config.tokenEndpoint(Customizer.withDefaults());
            config.userInfoEndpoint(endPoint -> endPoint.userAuthoritiesMapper(grantedAuthoritiesMapper));
        })
                .sessionManagement(config -> config.sessionCreationPolicy(SessionCreationPolicy.ALWAYS));

        return httpSecurity.build();
    }

}
