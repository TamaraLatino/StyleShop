package com.styleshop.model;


import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "usuarios")
public class Usuario {

//    @Column(nullable = false) , ese campo no puede estar
//    vacío en la base de datos
//    @Column(unique = true) , no puede haber dos usuarios
//    con el mismo correo

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUsuario;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private String apellidos;

    @Column(nullable = false, unique = true)
    private String correo;

    @Column(nullable = false)
    private String contrasena;

    private String telefono;

    @Column(nullable = false)
    private String rol; // CLIENTE o ADMIN

    @Column(nullable = false)
    private Boolean activo = true;

//    @Data es de Lombok —
//    genera automáticamente todos los métodos
//    necesarios (getters, setters, etc.)
}
