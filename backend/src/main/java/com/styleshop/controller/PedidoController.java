package com.styleshop.controller;


import com.styleshop.dto.PedidoDTO;
import com.styleshop.model.Pedido;
import com.styleshop.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RequestMapping ("/api/pedidos")
@RestController
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    // Crear pedido
    @PostMapping
    public ResponseEntity<Pedido> crearPedido(@RequestBody PedidoDTO dto) {
        return ResponseEntity.ok(pedidoService.crearPedido(dto));
    }

    // Listar pedidos de un cliente
    @GetMapping("/usuario/{idUsuario}")
    public ResponseEntity<List<Pedido>> listarPedidos(@PathVariable Long idUsuario) {
        return ResponseEntity.ok(pedidoService.listarPedidos(idUsuario));
    }

    // Ver detalle de un pedido
    @GetMapping("/{idPedido}")
    public ResponseEntity<Pedido> obtenerPedido(@PathVariable Long idPedido) {
        return ResponseEntity.ok(pedidoService.obtenerPedido(idPedido));
    }

    // Cambiar estado del pedido
    @PutMapping("/{idPedido}/estado")
    public ResponseEntity<Pedido> cambiarEstado(@PathVariable Long idPedido,
                                                @RequestParam String nuevoEstado) {
        return ResponseEntity.ok(pedidoService.cambiarEstado(idPedido, nuevoEstado));
    }

    // Reporte de pedidos para admin
    @GetMapping("/reporte")
    public ResponseEntity<List<Pedido>> reportePedidos(
            @RequestParam(required = false) String estado,
            @RequestParam(required = false) LocalDateTime desde,
            @RequestParam(required = false) LocalDateTime hasta) {
        return ResponseEntity.ok(pedidoService.reportePedidos(estado, desde, hasta));
    }

}
