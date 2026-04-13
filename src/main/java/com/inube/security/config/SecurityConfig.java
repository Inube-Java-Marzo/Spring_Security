package com.inube.security.config;

import com.inube.security.filter.JwtRequestFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static com.inube.security.util.Util.*;
import static org.springframework.security.config.Customizer.withDefaults;



// @Configuration indica que esta clase define configuración
// personalizada para Spring
@Configuration
public class SecurityConfig {

    // Inyectamos nuestro filtro JWT personalizado
    @Autowired
    private JwtRequestFilter jwtRequestFilter;
    // =========================================================
    // 🔐 CONFIGURACIÓN PRINCIPAL DE SEGURIDAD
    // =========================================================
    @Bean
    SecurityFilterChain web(HttpSecurity http) throws Exception {
        http
                // Habilita CORS con configuración por defecto
                .cors(withDefaults())
                // Deshabilita CSRF
                // ⚠ Se desactiva porque estamos usando JWT (stateless)
                .csrf(crf -> crf.disable())

                // Configuración de autorización
                .authorizeHttpRequests((authorize) -> authorize

                        // Permite acceso libre a rutas de autenticación
                        .requestMatchers(AUTHENDPOINT,
                                        REFRESHENDPOINT,
                                        MESSAGECREATE,
                                        PREREGISTERENDPOINT,
                                        VERIFYENDPOINT,
                                        ACTIVATEACCOUNTENDPOINT,
                                        CATESTADOENDPOINT,
                                        CATCIUDADENDPOINT).permitAll()

                        // Cualquier otra petición requiere autenticación
                        .anyRequest().authenticated()
                )
                // Agregamos nuestro filtro JWT antes del filtro
                // de autenticación tradicional
                .addFilterBefore(
                        jwtRequestFilter,
                        UsernamePasswordAuthenticationFilter.class
                )
                // Indicamos que NO usaremos sesiones
                // Cada request debe traer su token
                .sessionManagement((session) -> session
                        .sessionCreationPolicy(
                                SessionCreationPolicy.STATELESS)
                );
        // Construimos la configuración final
        return http.build();
    }


    // =========================================================
    // 🔑 ENCODER DE PASSWORDS
    // =========================================================
    @Bean
    PasswordEncoder passwordEncoder() {

        // BCrypt es un algoritmo de hashing seguro
        return new BCryptPasswordEncoder();
    }


    // =========================================================
    // 🔐 AUTHENTICATION MANAGER
    // =========================================================
    @Bean
    AuthenticationManager authenticationManager(
            AuthenticationConfiguration authenticationConfiguration)
            throws Exception {

        // Obtiene el AuthenticationManager
        // que Spring configura automáticamente
        return authenticationConfiguration.getAuthenticationManager();
    }
}
