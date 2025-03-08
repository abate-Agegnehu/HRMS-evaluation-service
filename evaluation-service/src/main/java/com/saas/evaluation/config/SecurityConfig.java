package com.saas.evaluation.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final RoleConverter roleConverter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http.csrf(AbstractHttpConfigurer::disable);
        http.authorizeHttpRequests(authorize -> authorize
                .requestMatchers("/swagger-ui/**",  "/swagger", "/v3/api-docs/**", "/actuator/**")
                .permitAll()
                .anyRequest()
                .authenticated());
        http.oauth2ResourceServer((oauth2 ->
                oauth2.jwt(customizer -> customizer.jwtAuthenticationConverter(roleConverter))));
        http.sessionManagement(t ->
                t.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        return http.build();
    }
}
