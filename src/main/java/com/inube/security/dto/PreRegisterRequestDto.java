package com.inube.security.dto;

import lombok.Data;

@Data
public class PreRegisterRequestDto {
    private String nombre;
    private String apaterno;
    private String amaterno;
    private String telefono;
    private String correo;
    private String uuidCiudad;
    private String uuidEstado;
}
