package com.styleshop.dto;

import lombok.Data;

@Data
public class LoginRequest {
    private String correo;
    private String contrasena;
}

/*
esto es como un formulario, se va a visualizar mejor cuando hagamos
el fronted, se manda cuando alguien quiere iniciar sesion
 */