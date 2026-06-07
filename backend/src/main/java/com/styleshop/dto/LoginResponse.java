package com.styleshop.dto;

import lombok.Data;
import lombok.AllArgsConstructor;

@Data
@AllArgsConstructor// esta anotacion en buena teoria genera
// automaticamente un constructor con todos los campos, tambien es de lombook

/*
* es lo que el sistema le devuelve al usuario cuando el
* login fue exitoso
* */
public class LoginResponse {
    private String token;// token es la llave temporal para que pueda hacer peticiones
    private String rol;// rol, es para distingir si es cliente o admin, para que en el
                       //fronted sepa que pantalla mostrar
    private String nombre;// el nombre del usuario para mostrarlo en la pantalla
}