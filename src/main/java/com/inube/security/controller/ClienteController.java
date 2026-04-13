package com.inube.security.controller;

import com.inube.security.model.ClienteModel;
import com.inube.security.service.IClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@CrossOrigin(origins = "*")
@RequestMapping("/api/v1/clientes")
public class ClienteController {
    @Autowired
    IClienteService clienteService;

    @RequestMapping("/list")
    public ResponseEntity<?> list(){
        List<ClienteModel> clientes = clienteService.findAll();
        return new ResponseEntity<>(clientes, HttpStatus.OK);
    }
}
