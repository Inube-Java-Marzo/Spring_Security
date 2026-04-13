package com.inube.security.controller;

import com.inube.security.model.CatalogoModel;
import com.inube.security.service.CatalogoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/catalogos")
@RequiredArgsConstructor
public class CatalogoController {
    private final CatalogoService service;

    @GetMapping("/{tipo}")
    public List<CatalogoModel> obtenerCatalogo(
            @PathVariable String tipo
    ) {
        return service.obtenerCatalogo(tipo);
    }

    @GetMapping("/ciudades/estado/{uuidEstado}")
    public List<CatalogoModel> obtenerCiudadesPorEstado(
            @PathVariable String uuidEstado
    ) {
        return service.obtenerCiudadesPorEstado(uuidEstado);
    }
}
