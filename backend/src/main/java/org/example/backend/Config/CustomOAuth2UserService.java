package org.example.backend.Config;

import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Service // Marca esto como un componente de servicio de Spring
public class CustomOAuth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {

    // Necesitas el servicio por defecto de Spring para obtener los datos de Google
    private final DefaultOAuth2UserService delegate = new DefaultOAuth2UserService();

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        // 1. Usa el servicio delegado para obtener el usuario de Google
        OAuth2User oauth2User = delegate.loadUser(userRequest);

        // 2. EXTRAE E IMPRIME LOS DATOS EN LA CONSOLA

        System.out.println("==================================================");
        System.out.println("INICIO DE SESIÓN EXITOSO CON GOOGLE DETECTADO");
        System.out.println("ID del Proveedor: " + userRequest.getClientRegistration().getRegistrationId()); // Muestra 'google'
        System.out.println("Nombre del Usuario: " + oauth2User.getAttribute("name"));
        System.out.println("Correo Electrónico: " + oauth2User.getAttribute("email"));
        System.out.println("Atributos Completos: " + oauth2User.getAttributes());
        System.out.println("==================================================");

        // Aquí es donde iría la lógica para guardar/actualizar el usuario en tu base de datos (DB)

        return oauth2User; // Devuelve el objeto OAuth2User a Spring Security
    }
}