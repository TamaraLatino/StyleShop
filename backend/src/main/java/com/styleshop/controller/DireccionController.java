package com.styleshop.controller;

import com.styleshop.model.Direccion;
import com.styleshop.service.DireccionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/direcciones")
public class DireccionController {

    @Autowired
    private DireccionService direccionService;

    //Agrega direcciones
    @PostMapping
        public ResponseEntity<Direccion> agregar(@RequestBody Direccion direccion){
            return ResponseEntity.ok(direccionService.agregarDireccion(direccion));
        }
        //Ver direcciones del cliente
    @GetMapping("/usuario/{idUsuario}")
    public ResponseEntity<List<Direccion>> verDirección(@PathVariable Long idUsuario){
        return ResponseEntity.ok(direccionService.verDirecciones(idUsuario));
    }

        //Eliminar direcciones
    @DeleteMapping("/{idDireccio}")
    public ResponseEntity<Void> eliminar(@PathVariable Long idDireccion){
        direccionService.eliminarDireccion(idDireccion);
        return ResponseEntity.noContent().build();
    }

}
