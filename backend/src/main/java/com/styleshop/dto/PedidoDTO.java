package com.styleshop.dto;


import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class PedidoDTO {
    private Long idUsuario;
    private Long idDireccion;
    private String notas;
    private List<DetallePedidoDTO> detalles;

    @Data
    public static class DetallePedidoDTO{
        private Long idInventario;
        private Integer cantidad;
        private BigDecimal precioUnitario;
    }
}
