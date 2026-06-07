package com.styleshop.model;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Entity
@Table(name = "pedido_detalles")
public class PedidoDetalle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPedidoDetalle;

    @ManyToOne
    @JoinColumn(name = "idPedido", nullable = false)
    private Pedido pedido;

    @ManyToOne
    @JoinColumn(name = "idInventario", nullable = false)
    private Inventario inventario;

    @Column(nullable = false)
    private Integer cantidad;

    @Column(nullable = false)
    private BigDecimal precioUnitario;

    private BigDecimal subtotal;

}
