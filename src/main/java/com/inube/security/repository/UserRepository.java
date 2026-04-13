package com.inube.security.repository;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Repository;
import com.inube.security.model.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

// @Repository le indica a Spring que esta clase
// es un componente de acceso a datos (DAO)
// También permite que Spring maneje excepciones de base de datos
@Repository
public class UserRepository implements IUserRepository {

    // @Autowired permite que Spring inyecte automáticamente
    // una instancia de JdbcTemplate
    @Autowired
    private JdbcTemplate jdbcTemplate;

    // Implementamos el método definido en la interfaz
    @Override
    public UserModel findByEmail(String correo) {

        String SQL = """
                SELECT * 
                FROM VW_USUARIOS_ACTIVOS
                WHERE UPPER(CORREO) = UPPER(?)
                """;

        try {
            return jdbcTemplate.queryForObject(
                    SQL,
                    new Object[]{correo},
                    new BeanPropertyRowMapper<>(UserModel.class)
            );
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }
}
