package com.inube.security.model;

import lombok.Data;

import java.util.Date;
@Data
public class ContactMessageModel {
    private String messageId;
    private String name;
    private String email;
    private String description;
    private Integer active;
    private Date dateSend;
    private String statusMessage;
    private String userResponse;
}
