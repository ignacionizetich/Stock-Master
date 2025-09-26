package org.example.backend.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
public class WebConfig {


@Bean
public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
    return http
            .csrf(AbstractHttpConfigurer::disable)
            .authorizeHttpRequests(auth -> auth
                    .requestMatchers("/", "/api/users/register", "api/items/add",
                            "/swagger-ui/**", "/swagger-ui.html","/v3/api-docs/**"
                    ).permitAll()
                    .anyRequest().authenticated()
            )
            .formLogin(form -> form
                    .loginPage("/login") ///  Este es el endpoint que renderiza el login (tiene que ser un controller ya que tiene que renderizar una vista HTML)
                    .permitAll()
            )
//            .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class) /// Esto es para los JWT que despues lo hago
            .build();
}




    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
