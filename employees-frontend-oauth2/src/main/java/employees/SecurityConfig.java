package employees;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientManager;
import org.springframework.security.oauth2.client.web.client.support.OAuth2RestClientHttpServiceGroupConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration(proxyBeanMethods = false)
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(request ->request
                        .requestMatchers("/create-employee")
                            .authenticated()
                        .requestMatchers("/", "/logout")
                            .permitAll()
                        .anyRequest().
                            denyAll()
                )
                .oauth2Login(Customizer.withDefaults())
                .logout(custom -> custom.logoutSuccessUrl("/"));
        return http.build();
    }

    // https://docs.spring.io/spring-security/reference/7.0/features/integrations/rest/http-service-client.html
    @Bean
    public OAuth2RestClientHttpServiceGroupConfigurer securityConfigurer(
            OAuth2AuthorizedClientManager manager) {
        return OAuth2RestClientHttpServiceGroupConfigurer.from(manager);
    }
}
