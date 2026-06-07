package com.styleshop.controller;

import com.styleshop.model.Categoria;
import com.styleshop.service.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/categorias")
public class CategoriaController {

    @Autowired
    private CategoriaService categoriaService;

    @GetMapping
    public ResponseEntity<List<Categoria>> listarTodas() {
        return ResponseEntity.ok(categoriaService.listarTodas());
    }

    @PostMapping
    public ResponseEntity<Categoria> crear(@RequestBody Categoria categoria) {
        return ResponseEntity.ok(categoriaService.created(categoria));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Categoria> editar(
            @PathVariable Long id,
            @RequestBody Categoria datos) {
        return ResponseEntity.ok(categoriaService.edit(id, datos));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        categoriaService.  delete(id);
        return ResponseEntity.ok().build();
    }
}