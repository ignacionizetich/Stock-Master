package org.example.backend.Controller.Api;

// Importa la clase para manejar la respuesta HTTP
import jakarta.servlet.http.HttpServletResponse;
// Importa la anotación para obtener el usuario autenticado
import org.springframework.security.core.annotation.AuthenticationPrincipal;
// Importa la clase que representa al usuario autenticado por OAuth2
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class GoogleAuthController {

    // Endpoint para iniciar el flujo de autenticación con Google
    @GetMapping("/oauth2/authorize/google")
    public void redirectToGoogle(HttpServletResponse response) throws IOException {
        // Redirige al usuario a la URL de autorización de Google configurada por Spring Security
        response.sendRedirect("/oauth2/authorization/google");
    }

    // Endpoint que recibe el callback de Google después de la autenticación
    @GetMapping("/login/oauth2/code/google")
    public String googleCallback(@AuthenticationPrincipal OAuth2User principal) {
        // Devuelve los atributos del usuario autenticado (por ejemplo, email, nombre, etc.)
        return "Usuario autenticado: " + principal.getAttributes().toString();
    }
}

