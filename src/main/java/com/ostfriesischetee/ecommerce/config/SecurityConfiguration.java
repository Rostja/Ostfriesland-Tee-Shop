package com.ostfriesischetee.ecommerce.config;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtDecoders;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.accept.ContentNegotiationStrategy;
import org.springframework.web.accept.HeaderContentNegotiationStrategy;

@Configuration
public class SecurityConfiguration {

    @Bean
    public JwtDecoder jwtDecoder() {
        // Auth0 JWKS URL, which should be a valid and accessible URL
        String jwksUri = "https://dev-e5n86jksvaryvb6x.us.auth0.com/.well-known/jwks.json";

        // Explicitly creating the JwtDecoder with the JWK Set URI
        return NimbusJwtDecoder.withJwkSetUri(jwksUri).build();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {


                http.authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/orders/**").authenticated()  // Chránený endpoint
                        .anyRequest().permitAll() // Ostatné povolené
                );

                http.oauth2ResourceServer(oauth2 -> oauth2
                        .jwt(Customizer.withDefaults()) // Používame JWT pre Auth0
                        .authenticationEntryPoint((request, response, authException) -> {
                            // Ak nie je platný token, vrátime vlastnú odpoveď 401
                            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                            response.setContentType("application/json");
                            response.getStatus();
                            response.getWriter().write(
                                    "{\"error\":\"unauthorized\",\"message\":\"Invalid or missing access token\"}"
                            );
                        })
                );

                http.cors(Customizer.withDefaults()); // Povolenie CORS (ak je potrebné)

                http.setSharedObject(
                        ContentNegotiationStrategy.class,
                        new HeaderContentNegotiationStrategy()
                );

                // Zakázanie CSRF, keď používame stateless API
                http.csrf(csrf -> csrf.disable());

        return http.build();
    }
}
