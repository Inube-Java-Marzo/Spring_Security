package com.inube.security.service;

import com.inube.security.model.CatalogoModel;

import java.util.List;

public interface ICatalogoService {
    List<CatalogoModel> obtenerCatalogo(String tipo);
    List<CatalogoModel> obtenerCiudadesPorEstado(String uuidEstado);
}
