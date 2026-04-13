package com.inube.security.service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import io.jsonwebtoken.Claims;

import java.util.HashMap;
import java.util.function.Function;
import java.util.Date;
// @Service indica que esta clase es un componente de la capa de servicio
// Spring la administrará automáticamente (Inyección de dependencias)
@Service
public class JwtUtilService {

    // 🔐 CLAVE SECRETA
    // Se usa para firmar digitalmente el token.
    // ⚠ En producción NO debe estar escrita aquí (hardcoded).
    // Debe ir en application.properties o variables de entorno.
    private static final String JWT_SECRET_KEY =
            "TExBVkVfTVVZX1NFQ1JFVEzE3Zmxu7BSGSJx72BSBXM";

    // ⏳ Tiempo de validez del token normal (15 minutos)
    // 1000 ms * 60 seg * 15 min
    private static final long JWT_TIME_VALIDITY =
            1000 * 60 * 15;

    // ⏳ Tiempo de validez del refresh token (24 horas)
    // 1000 ms * 60 seg * 60 min * 24 hrs
    private static final long JWT_TIME_REFRESH_VALIDATE =
            1000 * 60 * 60 * 24;


    // =========================================================
    // 🔹 GENERAR TOKEN PRINCIPAL
    // =========================================================
    public String generateToken(UserDetails userDetails, String role) {

        // Creamos un mapa para agregar información adicional (claims)
        var claims = new HashMap<String, Object>();

        // Agregamos el rol dentro del token
        claims.put("role", role);


        // Construimos el JWT
        return Jwts.builder()

                // Información adicional personalizada
                .setClaims(claims)

                // Usuario al que pertenece el token
                .setSubject(userDetails.getUsername())

                // Fecha de creación
                .setIssuedAt(new Date(System.currentTimeMillis()))

                // Fecha de expiración
                .setExpiration(new Date(
                        System.currentTimeMillis() + JWT_TIME_VALIDITY))

                // Firma digital usando algoritmo HS256 y clave secreta
                .signWith(SignatureAlgorithm.HS256, JWT_SECRET_KEY)

                // Genera el token final en formato String
                .compact();
    }


    // =========================================================
    // 🔹 GENERAR REFRESH TOKEN
    // =========================================================
    public String generateRefreshToken(UserDetails userDetails, String role) {

        var claims = new HashMap<String, Object>();
        claims.put("role", role);

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))

                // Expira en 24 horas
                .setExpiration(new Date(
                        System.currentTimeMillis() + JWT_TIME_REFRESH_VALIDATE))

                .signWith(SignatureAlgorithm.HS256, JWT_SECRET_KEY)
                .compact();
    }


    // =========================================================
    // 🔹 VALIDAR TOKEN
    // =========================================================
    public boolean validateToken(String token, UserDetails userDetails) {

        // Verifica que:
        // 1. El username del token sea igual al del usuario
        // 2. El token no esté expirado
        return extractClaim(token, Claims::getSubject)
                .equals(userDetails.getUsername())
                && !extractClaim(token, Claims::getExpiration)
                .before(new Date());
    }


    // =========================================================
    // 🔹 MÉTODO GENÉRICO PARA EXTRAER INFORMACIÓN DEL TOKEN
    // =========================================================
    private <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {

        // Parseamos el token y validamos la firma con la clave secreta
        Claims claims = Jwts.parser()
                .setSigningKey(JWT_SECRET_KEY)
                .build()
                .parseClaimsJws(token)
                .getBody();

        // Aplicamos la función para extraer la información deseada
        return claimsResolver.apply(claims);
    }


    // =========================================================
    // 🔹 EXTRAER USERNAME DEL TOKEN
    // =========================================================
    public String extractUsername(String token) {

        // Extrae el "subject" (usuario)
        return extractClaim(token, Claims::getSubject);
    }
}
