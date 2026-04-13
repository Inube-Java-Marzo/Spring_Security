package com.inube.security.repository;

import com.inube.security.model.CatalogoModel;

import java.util.List;

public interface ICatalogoRepository {
    List<CatalogoModel> obtenerCatalogo(
            String tabla,
            String columnaId,
            String columnaDescripcion
    );

    List<CatalogoModel> obtenerCiudadesPorEstado(String uuidEstado);
}
