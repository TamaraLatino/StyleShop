package com.styleshop.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "direcciones")
public class Direccion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idDireccion;

    @ManyToOne
    @JoinColumn(name = "idUsuario", nullable = false)
    private Usuario usuario;

    @Column(nullable = false)
    private String provincia;

    @Column(nullable = false)
    private String canton;

    @Column(nullable = false)
    private String direccionExacta;

    @Column(nullable = false)
    private Boolean esPrincipal = false;

}
