package com.inube.security.service;

import com.inube.security.dto.PreRegisterRequestDto;
import com.inube.security.repository.AuthRegisterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class RegisterService {
    @Autowired
    private AuthRegisterRepository repository;

    @Autowired
    private MailService mailService;

    public void preRegister(PreRegisterRequestDto request) {



        String token =
                UUID.randomUUID().toString();

        repository.savePersona(request);
        String idPersona = repository.findByEmail(request.getCorreo());
        repository.saveToken( token, idPersona);

        mailService.sendVerificationEmail(
                request.getCorreo(),
                token
        );
    }
}
