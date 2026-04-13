package com.inube.security.service;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;
import com.inube.security.model.UserModel;
import com.inube.security.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.ArrayList;
import java.util.List;

// @Service indica que esta clase pertenece a la capa de servicio
// Es un componente administrado por Spring
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    // Inyectamos nuestro repositorio
    // Lo usamos para consultar la base de datos

    @Autowired
    private IUserRepository iUserRepository;

    @Override
    public UserDetails loadUserByUsername(String correo)
            throws UsernameNotFoundException {

        UserModel userModel =
                this.iUserRepository.findByEmail(correo);

        if (userModel == null) {
            throw new UsernameNotFoundException("Correo no encontrado");
        }

        return new User(
                userModel.getCorreo(), // <- IMPORTANTE
                userModel.getPassword(),
                List.of(
                        new SimpleGrantedAuthority(
                                userModel.getNombreRol()
                        )
                )
        );
    }

    public UserModel findByEmail(String correo) {
        return this.iUserRepository.findByEmail(correo);
    }
}
