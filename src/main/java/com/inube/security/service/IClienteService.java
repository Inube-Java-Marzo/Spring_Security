package com.inube.security.service;

import com.inube.security.model.ClienteModel;

import java.util.List;

public interface IClienteService {
    public List<ClienteModel> findAll();
}
