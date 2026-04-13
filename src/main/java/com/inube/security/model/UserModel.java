package com.inube.security.model;

import lombok.Data;
// @Data es una anotación de Lombok
// Genera automáticamente:
// - Getters
// - Setters
// - toString()
// - equals()
// - hashCode()
@Data
public class UserModel {

    // Identificador único del usuario
    // Normalmente corresponde a la llave primaria en la base de datos
    private String userId;
    private String idPersona;
    private String nombre;
    private String correo;
    private String password;
    private String telefono;
    private String idRol;
    private String nombreRol;
}
