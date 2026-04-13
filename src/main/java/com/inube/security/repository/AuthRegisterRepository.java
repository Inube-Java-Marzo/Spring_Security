package com.inube.security.repository;

import com.inube.security.dto.PreRegisterRequestDto;
import com.inube.security.model.ContactMessageModel;
import com.inube.security.model.PersonaModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.UUID;

@Repository
public class AuthRegisterRepository implements IAuthRegisterRepository{

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void savePersona(PreRegisterRequestDto request) {

        String sql = """
            INSERT INTO PERSONA
            (
                NOMBRE,
                APATERNO,
                AMATERNO,
                TELEFONO,
                CORREO,
                UUID_CIUDAD,
                UUID_ESTADO,
                ESTADO,
                VERIFICADO,
                FECHA_ALTA
            )
            VALUES ( ?, ?, ?, ?, ?, ?, ?, 0, 99, SYSDATE)
        """;

        jdbcTemplate.update(
                sql,

                request.getNombre(),
                request.getApaterno(),
                request.getAmaterno(),
                request.getTelefono(),
                request.getCorreo(),
                request.getUuidCiudad(),
                request.getUuidEstado()
        );
    }
    @Override
    public void saveToken( String token, String idPersona) {

        String sql = """
            INSERT INTO TOKEN_VERIFICACION
            (
                
                ID_PERSONA,
                TOKEN,
                ID_TIPO_TOKEN,
                USADO,
                CADUCADO,
                FECHA_ALTA,
                FECHA_EXPIRACION,
                ESTADO
            )
            VALUES ( ?, ?, 'CTK-000', 0, 0,
                    SYSTIMESTAMP,
                    ?,
                    0)
        """;

        jdbcTemplate.update(
                sql,
                idPersona,
                token,
                Timestamp.valueOf(
                        LocalDateTime.now().plusHours(24)
                )
        );
    }

    @Override
    public String findByEmail(String email) {
        String sql = "SELECT ID_PERSONA FROM PERSONA WHERE CORREO = ?";

        try {
            // Cambiamos el Mapper por el tipo de dato que esperamos (String)
            return jdbcTemplate.queryForObject(sql, String.class, email);
        } catch (EmptyResultDataAccessException e) {
            return "Usuario no encontrado";
        }
    }

    @Override
    public String findPersonaByValidToken(String token) {

        String sql = """
        SELECT ID_PERSONA
        FROM TOKEN_VERIFICACION
        WHERE TOKEN = ?
          AND USADO = 0
          AND CADUCADO = 0
          AND FECHA_EXPIRACION > SYSTIMESTAMP
          AND ESTADO = 0
    """;

        try {
            return jdbcTemplate.queryForObject(
                    sql,
                    String.class,
                    token
            );
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public void createUser(
            String idPersona,
            String passwordHash) {

        String sql = """
        INSERT INTO USUARIO
        (
            
            ID_PERSONA,
            PASSWORD,
            ID_ROL,
            ESTADO,
            FECHA_ALTA
        )
        VALUES ( ?, ?, ?, 0, SYSDATE)
    """;

        jdbcTemplate.update(
                sql,

                idPersona,
                passwordHash,
                "RL-001" // default USER
        );
    }

    @Override
    public void verifyPersona(String idPersona) {

        String sql = """
        UPDATE PERSONA
        SET VERIFICADO = 0
        WHERE ID_PERSONA = ?
    """;

        jdbcTemplate.update(sql, idPersona);
    }

    @Override
    public void markTokenAsUsed(String token) {

        String sql = """
        UPDATE TOKEN_VERIFICACION
        SET USADO = 1,
            FECHA_USO = SYSTIMESTAMP
        WHERE TOKEN = ?
    """;

        jdbcTemplate.update(sql, token);
    }
}
