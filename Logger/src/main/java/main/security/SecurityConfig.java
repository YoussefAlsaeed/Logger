package main.security;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

import org.springframework.beans.factory.annotation.Value;


@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    // Injected via constructor. This component is responsible for converting JWT to authentication objects.
    private final JwtAuthConverter jwtAuthConverter;
    
    @Value("${security.role}")
    private String role;
    
    @Value("${security.url}")
    private String url;
    


    // This method configures the security filter chain, which is the heart of Spring Security's request processing.
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            // Disable CSRF protection. This is deprecated and being removed in newer versions.
            .csrf(csrf -> csrf.disable())
            // Configure authorization rules for HTTP requests.
            .authorizeHttpRequests(authorizeRequests ->
                authorizeRequests.requestMatchers(url).hasRole(role)  
                .anyRequest().authenticated()) // Allow any authenticated user to access any request
            
            // Configure OAuth2 resource server settings.
            .oauth2ResourceServer(oauth2 ->
                oauth2
                    // Configure JWT authentication.
                    .jwt(jwt -> jwt.jwtAuthenticationConverter(jwtAuthConverter))
            )
            // Configure session management.
            .sessionManagement(sessionManagement ->
                sessionManagement
                    .sessionCreationPolicy(STATELESS) // Use stateless sessions, no sessions are created or used
            );

        return http.build(); // Build the SecurityFilterChain and return it.
    }
}