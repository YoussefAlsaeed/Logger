package main.security;
import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.oauth2.jwt.JwtClaimValidator;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.web.SecurityFilterChain;

import lombok.RequiredArgsConstructor;


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
    
    @Value("${spring.security.oauth2.resourceserver.jwt.jwk-set-uri}")
    private String jwks;    
    
    // This bean is used to ignore the issuer check by providing custom decoder
    
    @Bean
    public JwtDecoder jwtDecoder() {
        NimbusJwtDecoder jwtDecoder = NimbusJwtDecoder.withJwkSetUri(jwks)
                .build();

        return jwtDecoder;
    }      


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