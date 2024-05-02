package br.com.eleodoro.dev.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig implements SecurityChain {

    @Bean
    @Override
    public SecurityFilterChain filterChain(HttpSecurity http) throws SecurityException {
        try {
            return http.authorizeHttpRequests(auth -> auth.requestMatchers("/auth").permitAll()
                            .requestMatchers("/v1/users/**").hasRole("ADMIN")
                    .requestMatchers("/**").authenticated())
                    .oauth2ResourceServer(oauth2-> oauth2.jwt(jwtConfigurer -> jwtConfigurer.jwtAuthenticationConverter(new JWTConverter()))).build();
        } catch (Exception e) {
            throw new SecurityException(e);
        }
    }
}
