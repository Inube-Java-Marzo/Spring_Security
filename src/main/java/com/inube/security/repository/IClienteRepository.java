package com.inube.security.repository;

import com.inube.security.model.ClienteModel;

import java.util.List;

public interface IClienteRepository {
    public List<ClienteModel> findAll();
}
