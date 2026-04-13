package com.inube.security.repository;

import com.inube.security.dto.PreRegisterRequestDto;

public interface IAuthRegisterRepository {
    public void savePersona(PreRegisterRequestDto request);
    public void saveToken( String token, String idPersona);

    public String findByEmail(String email);
    public String findPersonaByValidToken(String token);
    public void createUser( String idPersona, String passwordHash);
    public void verifyPersona(String idPersona);
    public void markTokenAsUsed(String token);
}
