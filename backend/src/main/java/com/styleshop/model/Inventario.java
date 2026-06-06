package com.styleshop.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "inventario")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Inventario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idInventario;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_producto", nullable = false)
    private Producto producto;

    @NotBlank(message = "La talla es obligatoria")
    @Column(nullable = false, length = 10)
    private String talla;

    @NotBlank(message = "El color es obligatorio")
    @Column(nullable = false, length = 50)
    private String color;

    @NotNull(message = "La cantidad es obligatoria")
    @Min(value = 0, message = "La cantidad no puede ser negativa")
    @Column(nullable = false)
    private Integer cantidadDisponible;

    @Column(nullable = false)
    @Builder.Default
    private Integer stockMinimo = 5;

    @Column(nullable = false)
    @Builder.Default
    private Boolean stockBajo = false;

    @Column(nullable = false)
    private LocalDateTime ultimaActualizacion;

    @PrePersist
    @PreUpdate
    public void actualizarFecha() {
        this.ultimaActualizacion = LocalDateTime.now();
    }
}
