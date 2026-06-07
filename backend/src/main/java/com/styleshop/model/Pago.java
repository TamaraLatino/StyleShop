package com.styleshop.model;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "pagos")
public class Pago {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPago;

    @OneToOne
    @JoinColumn(name = "idPedido", nullable = false)
    private Pedido pedido;

    @Column(nullable = false)
    private String metodoPago;

    @Column(nullable = false)
    private BigDecimal monto;

    @Column(nullable = false)
    private LocalDateTime fechaPago;

    @Column(nullable = false)
    private String estado = "PENDIENTE";
}
