package com.styleshop.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

public class InventarioDTO {

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class ActualizarStockRequest {

        @NotNull(message = "La cantidad es obligatoria")
        @Min(value = 0, message = "La cantidad no puede ser negativa")
        private Integer cantidadDisponible;

        @Min(value = 1, message = "El stock mínimo debe ser al menos 1")
        private Integer stockMinimo;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class CrearInventarioRequest {

        @NotNull(message = "El ID del producto es obligatorio")
        private Long idProducto;

        @NotBlank(message = "La talla es obligatoria")
        private String talla;

        @NotBlank(message = "El color es obligatorio")
        private String color;

        @NotNull(message = "La cantidad es obligatoria")
        @Min(value = 0, message = "La cantidad no puede ser negativa")
        private Integer cantidadDisponible;

        @Min(value = 1, message = "El stock mínimo debe ser al menos 1")
        @Builder.Default
        private Integer stockMinimo = 5;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class InventarioResponse {
        private Long idInventario;
        private Long idProducto;
        private String nombreProducto;
        private String talla;
        private String color;
        private Integer cantidadDisponible;
        private Integer stockMinimo;
        private Boolean stockBajo;
        private LocalDateTime ultimaActualizacion;
    }
}