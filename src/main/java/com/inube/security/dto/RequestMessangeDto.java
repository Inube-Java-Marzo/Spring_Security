package com.inube.security.dto;

import com.inube.security.model.ContactMessageModel;
import lombok.Data;

@Data
public class RequestMessangeDto {
    private String action; // ADD, READ, ANSWER, DELETE
    private ContactMessageModel data;
}
