package com.styleshop.controller;

import com.styleshop.dto.PagoDTO.PagoResponse;
import com.styleshop.dto.PagoDTO.RegistrarPagoRequest;
import com.styleshop.service.PagoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/pagos")
@RequiredArgsConstructor
public class PagoController {

    private final PagoService pagoService;

    @PostMapping
    public ResponseEntity<PagoResponse> registrarPago(
            @Valid @RequestBody RegistrarPagoRequest request) {
        PagoResponse response = pagoService.registrarPago(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/pedido/{idPedido}")
    public ResponseEntity<PagoResponse> obtenerPagoPorPedido(
            @PathVariable Long idPedido) {
        return ResponseEntity.ok(pagoService.obtenerPagoPorPedido(idPedido));
    }
}