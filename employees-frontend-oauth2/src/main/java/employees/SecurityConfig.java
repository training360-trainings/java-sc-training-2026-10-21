package employees;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
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
}
