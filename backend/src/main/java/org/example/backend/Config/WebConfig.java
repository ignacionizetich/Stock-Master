package org.example.backend.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;



@Configuration
public class WebConfig {


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(
                                "/",
                                "/api/users/register",
                                "api/items/add",
                                "/swagger-ui/**",
                                "/swagger-ui.html",
                                "/v3/api-docs/**",
                                "/oauth2/**", /// Permitir flujo OAuth2
                                "/login/oauth2/**" /// Permitir callback de Google
                        ).permitAll()
                        .anyRequest().authenticated()
                )
                // Opción: Habilitar solo OAuth2 Login
                .oauth2Login(oauth2 -> oauth2
                        .permitAll() // Esto no suele ser necesario si ya lo manejas con authorizeHttpRequests
                        .defaultSuccessUrl("/home", true)
                )
///                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
///           Esto es para los JWT que despues lo hago
                .build();
    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
