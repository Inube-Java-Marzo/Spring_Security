package com.inube.security.repository;


import com.inube.security.model.UsuarioListado;

import java.util.List;

public interface IUsuarioAdminRepository {
    int actualizarEstado(String userId, Integer estado);
    String obtenerCorreoUsuario(String userId);
    String obtenerNombreUsuario(String userId);
    List<UsuarioListado> obtenerUsuarios();
}
