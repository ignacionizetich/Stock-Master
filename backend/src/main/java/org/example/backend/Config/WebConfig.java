package org.example.backend.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.oidc.web.logout.OidcClientInitiatedLogoutSuccessHandler;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

@Configuration
public class WebConfig {

    private static final SessionCreationPolicy SESSION_POLICY = SessionCreationPolicy.IF_REQUIRED;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, OAuth2UserService<OAuth2UserRequest, OAuth2User> customOAuth2UserService, ClientRegistrationRepository clientRegistrationRepository) throws Exception {

        // 1. CONFIGURAR EL HANDLER DE LOGOUT DE OIDC
        // Este handler redirige el navegador a la URL de cierre de sesión de Google,
        // forzando la invalidación de la cookie __Host-GAPS.
        OidcClientInitiatedLogoutSuccessHandler oidcLogoutSuccessHandler = new OidcClientInitiatedLogoutSuccessHandler(clientRegistrationRepository);
        // Establecer la URL final a la que debe volver después de que Google cierre la sesión
        oidcLogoutSuccessHandler.setPostLogoutRedirectUri("http://localhost:8080/");

        return http
                .csrf(AbstractHttpConfigurer::disable)
                /// GESTIÓN DE SESIONES: Impide la creación de JSESSIONID para usuarios anónimos
                .sessionManagement(session -> session
                        /// Solo crea una sesión si el proceso de autenticación (OAuth) la requiere explícitamente
                        .sessionCreationPolicy(SESSION_POLICY)
                )
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
                        .defaultSuccessUrl("/home", true)
                        .userInfoEndpoint(userInfo -> userInfo
                                .userService(customOAuth2UserService)
                        )
                )
                .logout(logout -> logout
                        // Usamos logoutUrl() para establecer el endpoint
                        .logoutUrl("/logout")

                        // URL a la que redirigir después de cerrar la sesión (Acá deberíamos poner un /index)
                        .logoutSuccessHandler(oidcLogoutSuccessHandler)

                        // Invalida la sesión HTTP
                        .invalidateHttpSession(true)

                        // Borra todas las cookies de la sesión. ahora también revocamos la cookie de estado de OAuth2.
                        .deleteCookies("JSESSIONID", "OAuth2_AUTHORIZATION_REQUEST")
                )
                .build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
