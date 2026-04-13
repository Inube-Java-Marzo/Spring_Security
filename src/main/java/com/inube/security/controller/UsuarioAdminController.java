package com.inube.security.controller;

import com.inube.security.model.UsuarioListado;
import com.inube.security.service.UsuarioAdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/admin/usuarios")
@RequiredArgsConstructor
public class UsuarioAdminController {
    private final UsuarioAdminService service;

    @PatchMapping("/{userId}/ban")
    public ResponseEntity<Map<String, String>> banear(
            @PathVariable String userId
    ) {
        service.banearUsuario(userId);

        return ResponseEntity.ok(
                Map.of("mensaje", "Usuario inactivado")
        );
    }

    @PatchMapping("/{userId}/activar")
    public ResponseEntity<Map<String, String>> activar(
            @PathVariable String userId
    ) {
        service.activarUsuario(userId);

        return ResponseEntity.ok(
                Map.of("mensaje", "Usuario activado")
        );
    }

    @GetMapping
    public ResponseEntity<List<UsuarioListado>>
    listarUsuarios() {

        return ResponseEntity.ok(
                service.obtenerUsuarios()
        );
    }
}
