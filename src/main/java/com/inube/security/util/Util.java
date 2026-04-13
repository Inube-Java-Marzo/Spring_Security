package com.inube.security.util;

public class Util {
    public static Boolean OKSUCCESS = Boolean.TRUE;
    public static Boolean ERRORSUCCESS = Boolean.FALSE;

    public static String OKQUERY = "Consulta Correcta";
    public static String OKFOUND = "Registro Encontrado";
    public static String NOTFOUND = "Registro No Encontrado";
    public static String NOTUSER = "Usuario y/o Contraseña Incorrectos";
    public static String LOGINFAIL = "Intento de Login Erróneo de Usuario";
    public static String LOGINOK = "Login Correcto de Usuario";
    public static String TOKENINVALID = "Token Invalido";
    public static String TOKENVALID = "Token Válido";
    public static String SESSIONVALID = "Sesión Válida";
    public static String REGDISABLE = "Registro desactivado";
    public static String ERRVALIDATION = "Error de validación";
    public static String ERRINTERNAL = "Error interno";
    public static String ERRBUSINESS = "Error de negocio";
    public static String ERRDTO = "La data del dto no puede ser null";

    public static String AUTHENDPOINT ="/api/v1/auth/login";
    public static String REFRESHENDPOINT ="/api/v1/auth/refresh";
    public static String MESSAGECREATE ="/api/v1/messages/create";
    public static String PREREGISTERENDPOINT ="/api/v1/auth/pre-register";
    public static String VERIFYENDPOINT = "/api/v1/auth/verify";
    public static String ACTIVATEACCOUNTENDPOINT = "/api/v1/auth/activate-account";
    public static String CATESTADOENDPOINT = "/api/v1/catalogos/estados";
    public static String CATCIUDADENDPOINT = "/api/v1/catalogos/ciudades/**";

    public static Integer DEACTIVATECODE = 99;
    public static Integer ACTIVATECODE = 0;
}
