package com.styleshop.model;


import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@Table(name = "pedidos")
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPedido;

    @ManyToOne
    @JoinColumn(name = "idUsuario", nullable = false)
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "idDireccion", nullable = false)
    private Direccion direccion;

    @Column(nullable = false)
    private LocalDateTime fechaPedido;

    @Column(nullable = false)
    private String estado = "PENDIENTE";

    private BigDecimal subtotal;

    private BigDecimal total;

    private String notas;

    @OneToMany(mappedBy = "pedido")
    private List<PedidoDetalle> detalles;

    @OneToOne(mappedBy = "pedido")
    private Pago pago;
}
