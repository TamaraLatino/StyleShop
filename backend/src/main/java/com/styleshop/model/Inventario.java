package com.styleshop.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "inventario")
public class Inventario {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idInventario;

    @ManyToOne
    @JoinColumn(name = "idProducto", nullable = false)
    private Producto producto;

    @Column(nullable = false)
    private String talla;

    @Column(nullable = false)
    private String color;

    @Column(nullable = false)
    private Integer cantidadDisponible;

    @Column(nullable = false)
    private Integer stockMinimo = 5;

    @Column(nullable = false)
    private Boolean stockBajo = false;

}
