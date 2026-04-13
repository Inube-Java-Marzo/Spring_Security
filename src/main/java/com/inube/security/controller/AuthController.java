package com.inube.security.controller;

import com.inube.security.dto.*;
import com.inube.security.exception.ResourceNotFoundException;
import com.inube.security.model.UserModel;
import com.inube.security.repository.AuthRegisterRepository;
import com.inube.security.repository.UserRepository;
import com.inube.security.service.ActivateAccountService;
import com.inube.security.service.ClienteService;
import com.inube.security.service.JwtUtilService;
import com.inube.security.service.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

// @Controller indica que esta clase maneja peticiones HTTP
// En APIs REST normalmente se usa @RestController
@RestController
// Ruta base del controlador
@RequestMapping("/api/v1/auth")
public class AuthController {

    // Manager que ejecuta el proceso de autenticación
    @Autowired
    private AuthenticationManager authenticationManager;

    // Servicio que carga usuarios desde la BD
    @Autowired
    private UserDetailsService userDetailsService;

    // Servicio que genera y valida JWT
    @Autowired
    private JwtUtilService jwtUtilService;

    // Repositorio para consultar datos del usuario
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RegisterService registerService;

    @Autowired
    private ActivateAccountService activateAccountService;

    @Autowired
    private AuthRegisterRepository authRegisterRepository;


    // =========================================================
    // 🔐 LOGIN
    // =========================================================
    @PostMapping("/login")
    public ResponseEntity<?> auth(
            @RequestBody AuthRequestDto authRequestDto) {

        try {

            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            authRequestDto.getEmail(),
                            authRequestDto.getPassword()
                    )
            );

            UserDetails userDetails =
                    userDetailsService.loadUserByUsername(
                            authRequestDto.getEmail());

            UserModel userModel =
                    userRepository.findByEmail(
                            authRequestDto.getEmail());

            String jwt = jwtUtilService.generateToken(
                    userDetails,
                    userModel.getNombreRol()
            );

            String refreshToken =
                    jwtUtilService.generateRefreshToken(
                            userDetails,
                            userModel.getNombreRol()
                    );

            AuthResponseDto response = new AuthResponseDto();
            response.setToken(jwt);
            response.setRefreshToken(refreshToken);
            response.setSuccess(true);

            return ResponseEntity.ok(response);

        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .body("Error Authentication::: " + e.getMessage());
        }
    }


    // =========================================================
    // 🔄 REFRESH TOKEN
    // =========================================================

    @PostMapping("/refresh")
    public ResponseEntity<?> refresh(
            @RequestBody Map<String, String> request) {

        String refreshToken = request.get("refreshToken");

        try {

            // 1️⃣ Obtener correo desde el token
            String correo =
                    jwtUtilService.extractUsername(refreshToken);

            // 2️⃣ Buscar usuario por correo
            UserDetails userDetails =
                    userDetailsService.loadUserByUsername(correo);

            UserModel userModel =
                    userRepository.findByEmail(correo);

            // Validar que exista
            if (userModel == null) {
                return ResponseEntity
                        .status(HttpStatus.UNAUTHORIZED)
                        .body("Usuario no encontrado");
            }

            // 3️⃣ Validar refresh token
            if (jwtUtilService.validateToken(
                    refreshToken,
                    userDetails)) {

                String newJwt =
                        jwtUtilService.generateToken(
                                userDetails,
                                userModel.getNombreRol()
                        );

                String newRefreshToken =
                        jwtUtilService.generateRefreshToken(
                                userDetails,
                                userModel.getNombreRol()
                        );

                AuthResponseDto response =
                        new AuthResponseDto();

                response.setToken(newJwt);
                response.setRefreshToken(newRefreshToken);
                response.setSuccess(true);

                return ResponseEntity.ok(response);
            }

            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .body("Invalid Refresh Token");

        } catch (Exception e) {

            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .body("Error refresh token::: " + e.getMessage());
        }
    }

    @GetMapping("/me")
    public ResponsesDto me(Authentication authentication) {

        String correo = authentication.getName();

        UserModel me = userRepository.findByEmail(correo);

        if (me == null) {
            throw new ResourceNotFoundException("Usuario no encontrado");
        }

        me.setPassword(null);

        return buildResponse(true, "Usuario encontrado", me);
    }

    @PostMapping("/pre-register")
    public ResponseEntity<?> preRegister(
            @RequestBody PreRegisterRequestDto request) {

        try {

            registerService.preRegister(request);

            ResponsesDto response = new ResponsesDto();
            response.setSuccess(true);
            response.setMensaje("Correo de verificación enviado");

            return ResponseEntity.ok(response);

        } catch (Exception e) {

            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(e.getMessage());
        }
    }

    @GetMapping("/verify")
    public ResponseEntity<?> verify(
            @RequestParam String token) {

        String idPersona =
                authRegisterRepository
                        .findPersonaByValidToken(token);

        if (idPersona == null) {
            return ResponseEntity
                    .badRequest()
                    .body("Token inválido o expirado");
        }
        ResponsesDto response = new ResponsesDto();
        response.setSuccess(true);
        response.setMensaje("Token valido");

        return ResponseEntity.ok(response);
    }

    @PostMapping("/activate-account")
    public ResponseEntity<?> activateAccount(
            @RequestBody ActivateAccountDto request) {

        try {

            activateAccountService
                    .activateAccount(request);

            ResponsesDto response = new ResponsesDto();
            response.setSuccess(true);
            response.setMensaje("Cuenta activada");

            return ResponseEntity.ok(response);

        } catch (Exception e) {

            return ResponseEntity
                    .badRequest()
                    .body(e.getMessage());
        }
    }

    private ResponsesDto buildResponse(Boolean success, String mensaje, Object data) {
        ResponsesDto res = new ResponsesDto();
        res.setSuccess(success);
        res.setMensaje(mensaje);
        res.setData(data);
        return res;
    }

}
