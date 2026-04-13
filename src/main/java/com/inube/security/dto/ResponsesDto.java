package com.inube.security.dto;

import lombok.Data;

@Data
public class ResponsesDto {
    Boolean success;
    String mensaje;
    String error;
    Object data;
}
