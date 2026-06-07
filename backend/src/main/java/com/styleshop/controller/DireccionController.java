package com.styleshop.controller;

import com.styleshop.model.Direccion;
import com.styleshop.service.DireccionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/direcciones")
public class DireccionController {

    @Autowired
    private DireccionService direccionService;

    //Agrega direcciones
    @PostMapping
        public ResponseEntity<Direccion> agregar(@RequestBody Direccion direccion){
            return ResponseEntity.ok(direccionService.verDirecciones(idUsuario));
        }
        //Ver direcciones del cliente
    @DeleteMapping

        //Eliminar direcciones
    @DeleteMapping("/{idDireccio}")
    public ResponseEntity<Void> eliminar(@PathVariable Long idDirección){
        direccionService.eliminarDireccion(idDirección);
        return ResponseEntity.noContent().build();
    }

}
