package com.styleshop.model;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Entity
@Table(name = "carrito_detalles")
public class CarritoDetalle {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCarritoDetalle;

    @ManyToOne
    @JoinColumn(name = "idCarrito", nullable = false)
    private Carrito carrito;

    @ManyToOne
    @JoinColumn(name = "idInventario", nullable = false)
    private Inventario inventario;

    @Column(nullable = false)
    private Integer cantidad;

    @Column(nullable = false)
    private BigDecimal precioUnitario;
}
