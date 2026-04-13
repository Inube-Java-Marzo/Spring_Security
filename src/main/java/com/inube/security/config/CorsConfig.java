package com.inube.security.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

// Indica que esta clase es una clase de configuración de Spring
// Es decir, aquí definimos beans y configuraciones personalizadas
@Configuration
public class CorsConfig {

    // @Bean le dice a Spring que el método devuelve un objeto
    // que debe registrarse en el contenedor de Spring (IoC)
    @Bean
    CorsConfigurationSource corsConfigurationSource() {

        // Creamos un objeto CorsConfiguration
        // Aquí definimos las reglas de CORS (Cross-Origin Resource Sharing)
        CorsConfiguration configuration = new CorsConfiguration();

        // Permite enviar credenciales en las peticiones
        // (cookies, headers de autorización, sesiones, etc.)
        configuration.setAllowCredentials(true);

        // Define qué orígenes (dominios) pueden consumir nuestra API
        // En este caso solo permitimos Angular corriendo en localhost:4200
        configuration.setAllowedOrigins(Arrays.asList("http://app.inube"));

        // Define los métodos HTTP permitidos
        // Solo estas operaciones podrán ejecutarse desde el frontend
        configuration.setAllowedMethods(Arrays.asList("GET","POST", "PUT", "DELETE","PATCH"));

        // Define qué headers están permitidos
        // "*" significa que se permiten todos los headers
        configuration.setAllowedHeaders(Arrays.asList("*"));

        // UrlBasedCorsConfigurationSource permite aplicar la configuración
        // CORS a rutas específicas de la aplicación
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();

        // Registramos la configuración CORS para todas las rutas (/**)
        // Esto significa que aplica a todos los endpoints del backend
        source.registerCorsConfiguration("/**", configuration);

        // Retornamos la configuración para que Spring la use
        return source;
    }
}
