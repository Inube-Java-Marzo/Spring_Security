package com.inube.security.repository;
import com.inube.security.model.UserModel;

// Definimos una interfaz
// En Java, una interfaz solo declara métodos (no implementaciones)
public interface IUserRepository {

    // Método que buscará un usuario por su nombre
    // Recibe como parámetro el username
    // Devuelve un objeto UserModel si lo encuentra
    UserModel findByEmail(String correo);


}
