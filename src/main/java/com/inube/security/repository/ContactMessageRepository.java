package com.inube.security.repository;

import com.inube.security.model.ContactMessageModel;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ContactMessageRepository implements IContactMessageRepository{

    private final JdbcTemplate jdbcTemplate;

    @Override
    public int save(ContactMessageModel message) {

        String sql = """
            INSERT INTO MENSAJE_CONTACTO
            (NOMBRE, CORREO, DESCRIPCION)
            VALUES (?, ?, ?)
        """;

        return jdbcTemplate.update(sql,
                message.getName(),
                message.getEmail(),
                message.getDescription()
        );
    }

    @Override
    public int updateStatus(String messageId, String status, String userResponse) {

        String sql = """
            UPDATE MENSAJE_CONTACTO
            SET ID_STATUS_MENSAJE = ?,
                USUARIO_RESPUESTA = ?
            WHERE ID_MENSAJE = ?
            AND ACTIVO = 1
        """;

        return jdbcTemplate.update(sql, status, userResponse, messageId);
    }

    @Override
    public int logicalDelete(String messageId) {

        String sql = """
            UPDATE MENSAJE_CONTACTO
            SET ACTIVO = 0
            WHERE ID_MENSAJE = ?
        """;

        return jdbcTemplate.update(sql, messageId);
    }

    @Override
    public List<ContactMessageModel> findAll() {

        String sql = """
            SELECT
                ID_MENSAJE AS messageId,
                NOMBRE AS name,
                CORREO AS email,
                DESCRIPCION AS description,
                ACTIVO AS active,
                FECHA_ENVIO AS dateSend,
                ID_STATUS_MENSAJE AS statusMessage,
                USUARIO_RESPUESTA AS userResponse
            FROM MENSAJE_CONTACTO
            WHERE ACTIVO = 1
            ORDER BY FECHA_ENVIO DESC
        """;

        return jdbcTemplate.query(
                sql,
                new BeanPropertyRowMapper<>(ContactMessageModel.class)
        );
    }

    @Override
    public List<ContactMessageModel> findByStatus(String status) {

        String sql = """
            SELECT
                ID_MENSAJE AS messageId,
                NOMBRE AS name,
                CORREO AS email,
                DESCRIPCION AS description,
                ACTIVO AS active,
                FECHA_ENVIO AS dateSend,
                ID_STATUS_MENSAJE AS statusMessage,
                USUARIO_RESPUESTA AS userResponse
            FROM MENSAJE_CONTACTO
            WHERE ID_STATUS_MENSAJE = ?
            AND ACTIVO = 1
        """;

        return jdbcTemplate.query(
                sql,
                new BeanPropertyRowMapper<>(ContactMessageModel.class),
                status
        );
    }

    @Override
    public ContactMessageModel findById(String messageId) {

        String sql = """
        SELECT
            ID_MENSAJE AS messageId,
            NOMBRE AS name,
            CORREO AS email,
            DESCRIPCION AS description,
            ACTIVO AS active,
            FECHA_ENVIO AS dateSend,
            ID_STATUS_MENSAJE AS statusMessage,
            USUARIO_RESPUESTA AS userResponse
        FROM MENSAJE_CONTACTO
        WHERE ID_MENSAJE = ?
    """;

        return jdbcTemplate.queryForObject(
                sql,
                new BeanPropertyRowMapper<>(ContactMessageModel.class),
                messageId
        );
    }
}
