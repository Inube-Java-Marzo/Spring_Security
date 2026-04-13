package com.inube.security.model;

import lombok.Data;

@Data
public class CatalogoModel {
    private String id;
    private String descripcion;

    public CatalogoModel(String id, String descripcion) {
        this.id = id;
        this.descripcion = descripcion;
    }
}
