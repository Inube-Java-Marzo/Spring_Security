package com.inube.security.service;

import com.inube.security.model.UsuarioListado;

import java.util.List;

public interface IUsuarioAdminService {
    void banearUsuario(String userId);
    void activarUsuario(String userId);
    List<UsuarioListado> obtenerUsuarios();
}
