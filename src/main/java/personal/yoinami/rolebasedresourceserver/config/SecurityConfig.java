package personal.yoinami.rolebasedresourceserver.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity(debug = true)
public class SecurityConfig {

    @Bean
    public SecurityFilterChain enableSecurity(HttpSecurity httpSecurity) throws Exception {

        httpSecurity.authorizeHttpRequests(req -> req.anyRequest().fullyAuthenticated());

        httpSecurity.oauth2Client(Customizer.withDefaults());
        httpSecurity.oauth2Login(config -> {
            config.tokenEndpoint(Customizer.withDefaults());
            config.userInfoEndpoint(Customizer.withDefaults());
        })
                .sessionManagement(config -> config.sessionCreationPolicy(SessionCreationPolicy.ALWAYS));

        return httpSecurity.build();
    }
}
