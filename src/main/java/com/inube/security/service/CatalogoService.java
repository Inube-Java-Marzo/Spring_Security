package com.inube.security.service;

import com.inube.security.model.CatalogoModel;
import com.inube.security.repository.CatalogoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CatalogoService implements ICatalogoService{
    private final CatalogoRepository repository;

    @Override
    public List<CatalogoModel> obtenerCatalogo(String tipo) {

        return switch (tipo.toLowerCase()) {

            case "roles" ->
                    repository.obtenerCatalogo(
                            "CAT_ROL",
                            "ID_ROL",
                            "NOMBRE_ROL"
                    );

            case "tipo-cliente" ->
                    repository.obtenerCatalogo(
                            "CAT_TIPO_CLIENTE",
                            "ID_TIPO_CLIENTE",
                            "NOMBRE"
                    );

            case "estado-pago" ->
                    repository.obtenerCatalogo(
                            "CAT_ESTADO_PAGO",
                            "ID_ESTADO_PAGO",
                            "NOMBRE"
                    );

            case "estado-factura" ->
                    repository.obtenerCatalogo(
                            "CAT_ESTADO_FACTURA",
                            "ID_ESTADO_FACTURA",
                            "NOMBRE"
                    );

            case "tipo-token" ->
                    repository.obtenerCatalogo(
                            "CAT_TIPO_TOKEN",
                            "ID_TIPO_TOKEN",
                            "NOMBRE_TOKEN"
                    );

            case "status-mensaje" ->
                    repository.obtenerCatalogo(
                            "CAT_STATUS_MENSAJE",
                            "ID_STATUS_MENSAJE",
                            "NOMBRE_STATUS"
                    );

            case "paises" ->
                    repository.obtenerCatalogo(
                            "CAT_PAIS",
                            "UUID_PAIS",
                            "DESCRIPCION"
                    );

            case "estados" ->
                    repository.obtenerCatalogo(
                            "CAT_ESTADO",
                            "UUID_ESTADO",
                            "DESCRIPCION"
                    );

            case "ciudades" ->
                    repository.obtenerCatalogo(
                            "CAT_CIUDAD",
                            "UUID_CIUDAD",
                            "DESCRIPCION"
                    );

            default -> throw new RuntimeException("Catálogo no encontrado");
        };
    }

    @Override
    public List<CatalogoModel> obtenerCiudadesPorEstado(String uuidEstado) {
        return repository.obtenerCiudadesPorEstado(uuidEstado);
    }
}
