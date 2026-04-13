package com.inube.security.service;

import com.inube.security.dto.ActivateAccountDto;
import com.inube.security.model.ClienteModel;
import com.inube.security.repository.AuthRegisterRepository;
import com.inube.security.repository.IClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService implements IClienteService {
    @Autowired
    private IClienteRepository clienteRepository;

    @Override
    public List<ClienteModel> findAll() {
        List<ClienteModel> list;
        try{
            list = clienteRepository.findAll();
        }catch (Exception ex){
            throw ex;
        }
        return list;
        //return new ResponsesDto(Util.OKSUCCESS, Util.OKQUERY, null, viwLogin);
    }


}
