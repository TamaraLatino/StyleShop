package com.styleshop.model;


import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@Table(name = "carritos")
public class Carrito {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCarrito;

    @OneToOne
    @JoinColumn(name = "idUsuario", nullable = false)
    private Usuario usuario;

    @Column(nullable = false)
    private LocalDateTime fechaCreacion;

    @Column(nullable = false)
    private String estado = "ACTIVO";

    @OneToMany(mappedBy = "carrito")
    private List<CarritoDetalle> detalles;

}
