package ws.beauty.salon.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(
                auth -> auth
                        .requestMatchers("/signin", "/signup","/swagger-ui/**", "/v3/api-docs/**").permitAll()
                        .requestMatchers("/api/v1/stylists/**").hasRole("ADMIN")
                        .requestMatchers("/api/v1/users/**").hasRole("ADMIN")
                        .requestMatchers("/graphql/**").hasRole("ADMIN")
                        .requestMatchers("/api/v1/services/**").hasRole("STYLIST")
                        .anyRequest().authenticated())
                .httpBasic(withDefaults()).csrf(csrf -> csrf.disable())

                .logout(logout -> logout.logoutUrl("/signout").permitAll());
        return http.build();
    }

}