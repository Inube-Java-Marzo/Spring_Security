package com.inube.security.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.beans.factory.annotation.Autowired;
import com.inube.security.service.JwtUtilService;
import java.io.IOException;

// @Component indica que Spring debe registrar esta clase
// como un bean administrado automáticamente
@Component
public class JwtRequestFilter extends OncePerRequestFilter {

    // Servicio que carga usuarios desde la base de datos
    @Autowired
    private UserDetailsService userDetailsService;

    // Servicio que genera y valida JWT
    @Autowired
    private JwtUtilService jwtUtilService;


    // Este método se ejecuta UNA VEZ por cada request HTTP
    // (Por eso extiende OncePerRequestFilter)
    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain)
            throws ServletException, IOException {

        // 1️⃣ Obtenemos el header Authorization
        final String authorizationHeader =
                request.getHeader("Authorization");


        // 2️⃣ Validamos que el header exista
        // y que comience con "Bearer "
        if (authorizationHeader != null
                && authorizationHeader.startsWith("Bearer ")) {

            // Extraemos el token quitando "Bearer "
            String jwt = authorizationHeader.substring(7);

            // Bearer nofinioesnesifo575457

            // Extraemos el username del token
            String username =
                    jwtUtilService.extractUsername(jwt);


            // 3️⃣ Verificamos:
            // - Que el username no sea null
            // - Que no haya ya una autenticación en el contexto
            if (username != null
                    && SecurityContextHolder
                    .getContext()
                    .getAuthentication() == null) {

                // Cargamos el usuario desde la base de datos
                UserDetails userDetails =
                        this.userDetailsService
                                .loadUserByUsername(username);

                // 4️⃣ Validamos el token
                if (jwtUtilService
                        .validateToken(jwt, userDetails)) {

                    // Creamos el objeto de autenticación
                    UsernamePasswordAuthenticationToken
                            authenticationToken =
                            new UsernamePasswordAuthenticationToken(
                                    userDetails,
                                    null,
                                    userDetails.getAuthorities());

                    // Agregamos detalles de la request
                    authenticationToken.setDetails(
                            new WebAuthenticationDetailsSource()
                                    .buildDetails(request));

                    // 5️⃣ Guardamos la autenticación
                    // en el contexto de seguridad
                    SecurityContextHolder
                            .getContext()
                            .setAuthentication(authenticationToken);
                }
            }
        }

        // 6️⃣ Continuamos con la cadena de filtros
        // (Si no hacemos esto, la petición se bloquea)
        filterChain.doFilter(request, response);
    }
}
