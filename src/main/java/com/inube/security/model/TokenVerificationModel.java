package com.inube.security.model;

import java.sql.Timestamp;

public class TokenVerificationModel {
    private String idTokenVerificacion;
    private String idPersona;
    private String token;
    private String idTipoToken;
    private Integer usado;
    private Integer caducado;
    private Timestamp fechaAlta;
    private Timestamp fechaExpiracion;
}
