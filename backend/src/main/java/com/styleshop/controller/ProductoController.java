package com.styleshop.controller;

import com.styleshop.model.Producto;
import com.styleshop.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/productos")
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    // Ver todos los productos
    @GetMapping
    public ResponseEntity<List<Producto>> listarTodos() {
        return ResponseEntity.ok(productoService.listarTodos());
    }

    // Ver un producto por ID
    @GetMapping("/{id}")
    public ResponseEntity<Producto> verUno(@PathVariable Long id) {
        return ResponseEntity.ok(productoService.identifyById(id));
    }

    // Ver productos por categoria
    @GetMapping("/categoria/{idCategoria}")
    public ResponseEntity<List<Producto>> porCategoria(
            @PathVariable Long idCategoria) {
        return ResponseEntity.ok(
                productoService.listarPorCategoria(idCategoria));
    }

    // Crear producto nuevo
    @PostMapping
    public ResponseEntity<Producto> crear(@RequestBody Producto producto) {
        return ResponseEntity.ok(productoService.created(producto));
    }

    // Editar un producto
    @PutMapping("/{id}")
    public ResponseEntity<Producto> editar(
            @PathVariable Long id,
            @RequestBody Producto datos) {
        return ResponseEntity.ok(productoService.edit(id, datos));
    }

    // Eliminar un producto
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        productoService.delete(id);
        return ResponseEntity.ok().build();
    }
}