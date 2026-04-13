package com.inube.security.model;

import lombok.Data;

import java.util.Date;
import java.util.UUID;

@Data
public class ClienteModel {
    UUID id_cliente;
    String nombre;
    String apaterno;
    String amaterno;
    String rfc;
    Date fecha_nacimiento;
    Integer activo;
}
