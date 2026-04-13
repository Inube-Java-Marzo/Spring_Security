package com.inube.security.repository;

import com.inube.security.model.ClienteModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ClienteRepository implements IClienteRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<ClienteModel> findAll() {
        String SQL = "Select * from CLIENTES";
        return jdbcTemplate.query(SQL, BeanPropertyRowMapper.newInstance(ClienteModel.class));
    }
}
