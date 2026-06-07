package com.styleshop.dto;

import com.styleshop.model.Pago.EstadoPago;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class PagoDTO {

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class RegistrarPagoRequest {

        @NotNull(message = "El ID del pedido es obligatorio")
        private Long idPedido;

        @NotBlank(message = "El método de pago es obligatorio")
        private String metodoPago;

        @NotNull(message = "El monto es obligatorio")
        @DecimalMin(value = "0.01", message = "El monto debe ser mayor a cero")
        private BigDecimal monto;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class PagoResponse {
        private Long idPago;
        private Long idPedido;
        private String metodoPago;
        private BigDecimal monto;
        private LocalDateTime fechaPago;
        private EstadoPago estado;
        private String referencia;
        private String mensaje;
    }
}