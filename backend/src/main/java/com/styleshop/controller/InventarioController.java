package com.styleshop.controller;

import com.styleshop.dto.InventarioDTO.ActualizarStockRequest;
import com.styleshop.dto.InventarioDTO.CrearInventarioRequest;
import com.styleshop.dto.InventarioDTO.InventarioResponse;
import com.styleshop.service.InventarioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventario")
@RequiredArgsConstructor
public class InventarioController {

    private final InventarioService inventarioService;

    @PostMapping
    public ResponseEntity<InventarioResponse> crearInventario(
            @Valid @RequestBody CrearInventarioRequest request) {
        InventarioResponse response = inventarioService.crearInventario(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/producto/{idProducto}")
    public ResponseEntity<List<InventarioResponse>> obtenerStockPorProducto(
            @PathVariable Long idProducto) {
        return ResponseEntity.ok(inventarioService.obtenerStockPorProducto(idProducto));
    }

    @GetMapping("/producto/{idProducto}/talla/{talla}/color/{color}")
    public ResponseEntity<InventarioResponse> obtenerStockPorTallaYColor(
            @PathVariable Long idProducto,
            @PathVariable String talla,
            @PathVariable String color) {
        return ResponseEntity.ok(
                inventarioService.obtenerStockPorTallaYColor(idProducto, talla, color));
    }

    @PutMapping("/{idInventario}/stock")
    public ResponseEntity<InventarioResponse> actualizarStock(
            @PathVariable Long idInventario,
            @Valid @RequestBody ActualizarStockRequest request) {
        return ResponseEntity.ok(inventarioService.actualizarStock(idInventario, request));
    }

    @GetMapping("/stock-bajo")
    public ResponseEntity<List<InventarioResponse>> obtenerProductosConStockBajo() {
        return ResponseEntity.ok(inventarioService.obtenerProductosConStockBajo());
    }
}