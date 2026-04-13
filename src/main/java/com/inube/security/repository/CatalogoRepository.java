package com.inube.security.repository;

import com.inube.security.model.CatalogoModel;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class CatalogoRepository  implements ICatalogoRepository{
    private final JdbcTemplate jdbcTemplate;
    @Override
    public List<CatalogoModel> obtenerCatalogo(
            String tabla,
            String columnaId,
            String columnaDescripcion
    ) {

        String sql = String.format("""
                SELECT %s AS ID,
                       %s AS DESCRIPCION
                FROM %s
                WHERE ESTADO = 0
               
                """,
                columnaId,
                columnaDescripcion,
                tabla,
                columnaDescripcion
        );

        return jdbcTemplate.query(sql,
                (rs, rowNum) -> new CatalogoModel(
                        rs.getString("ID"),
                        rs.getString("DESCRIPCION")
                )
        );
    }

    @Override
    public List<CatalogoModel> obtenerCiudadesPorEstado(String uuidEstado) {

        String sql = """
            SELECT UUID_CIUDAD AS ID,
                   DESCRIPCION
            FROM CAT_CIUDAD
            WHERE UUID_ESTADO = ?
              AND ESTADO = 0
            ORDER BY DESCRIPCION
            """;

        return jdbcTemplate.query(
                sql,
                new Object[]{uuidEstado},
                (rs, rowNum) -> new CatalogoModel(
                        rs.getString("ID"),
                        rs.getString("DESCRIPCION")
                )
        );
    }
}
