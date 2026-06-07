package com.styleshop.controller;

import com.styleshop.model.Carrito;
import com.styleshop.model.CarritoDetalle;
import com.styleshop.service.CarritoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.math.BigDecimal;

@RestController
@RequestMapping("/api/carrito")
public class CarritoController {

    @Autowired
    private CarritoService carritoService;

    // Ver carrito
    @GetMapping("/{idCarrito}")
    public ResponseEntity<Carrito> verCarrito(@PathVariable Long idCarrito) {
        return ResponseEntity.ok(carritoService.verCarrito(idCarrito));
    }

    // Agregar producto
    @PostMapping("/{idCarrito}/agregar")
    public ResponseEntity<CarritoDetalle> agregar(
            @PathVariable Long idCarrito,
            @RequestParam Long idInventario,
            @RequestParam Integer cantidad,
            @RequestParam BigDecimal precioUnitario) {
        return ResponseEntity.ok(carritoService.agregarProducto(idCarrito, idInventario, cantidad, precioUnitario));
    }

    // Eliminar producto
    @DeleteMapping("/detalle/{idCarritoDetalle}")
    public ResponseEntity<Void> eliminar(@PathVariable Long idCarritoDetalle) {
        carritoService.eliminarProducto(idCarritoDetalle);
        return ResponseEntity.noContent().build();
    }
}