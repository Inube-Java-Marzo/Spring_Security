package com.inube.security.repository;

import com.inube.security.model.UsuarioListado;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class UsuarioAdminRepository implements IUsuarioAdminRepository{
    private final JdbcTemplate jdbcTemplate;

    @Override
    public int actualizarEstado(
            String userId,
            Integer estado
    ) {
        String sql = """
            UPDATE USUARIO
            SET ESTADO = ?
            WHERE USER_ID = ?
            """;

        return jdbcTemplate.update(
                sql,
                estado,
                userId
        );}
    @Override
    public String obtenerCorreoUsuario(String userId) {

        String sql = """
                SELECT P.CORREO
                FROM USUARIO U
                INNER JOIN PERSONA P
                    ON U.ID_PERSONA = P.ID_PERSONA
                WHERE U.USER_ID = ?
                """;

        return jdbcTemplate.queryForObject(
                sql,
                String.class,
                userId
        );
    }

    @Override
    public String obtenerNombreUsuario(String userId){
        String sql = """
                SELECT P.NOMBRE
                FROM USUARIO U
                INNER JOIN PERSONA P
                    ON U.ID_PERSONA = P.ID_PERSONA
                WHERE U.USER_ID = ?
                """;

        return jdbcTemplate.queryForObject(
                sql,
                String.class,
                userId
        );
    }

    @Override
    public List<UsuarioListado> obtenerUsuarios() {

        String sql = """
            SELECT
                U.USER_ID,
                P.NOMBRE || ' ' ||
                P.APATERNO || ' ' ||
                P.AMATERNO AS NOMBRE_COMPLETO,
                P.CORREO,
                R.NOMBRE_ROL,
                U.ESTADO,
                U.FECHA_ALTA
            FROM USUARIO U
            INNER JOIN PERSONA P
                ON U.ID_PERSONA = P.ID_PERSONA
            INNER JOIN CAT_ROL R
                ON U.ID_ROL = R.ID_ROL
            ORDER BY U.FECHA_ALTA DESC
            """;

        return jdbcTemplate.query(sql,
                (rs, rowNum) ->
                        new UsuarioListado(
                                rs.getString("USER_ID"),
                                rs.getString("NOMBRE_COMPLETO"),
                                rs.getString("CORREO"),
                                rs.getString("NOMBRE_ROL"),
                                rs.getInt("ESTADO"),
                                rs.getDate("FECHA_ALTA")
                        )
        );
    }


}
