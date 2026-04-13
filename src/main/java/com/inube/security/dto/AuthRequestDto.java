package com.inube.security.dto;

import lombok.Data;
// @Data es una anotación de Lombok
// Genera automáticamente:
// - Getters
// - Setters
// - toString()
// - equals()
// - hashCode()
// - Constructor requerido
@Data
public class AuthRequestDto {

    // Variable que almacenará el nombre de usuario
    // Se usa cuando el cliente envía las credenciales
    String email;

    // Variable que almacenará la contraseña
    // Se envía desde el frontend al backend para autenticación
    String password;
}