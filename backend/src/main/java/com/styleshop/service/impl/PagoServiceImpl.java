package com.styleshop.service.impl;

import com.styleshop.dto.PagoDTO.PagoResponse;
import com.styleshop.dto.PagoDTO.RegistrarPagoRequest;
import com.styleshop.model.Pago;
import com.styleshop.model.Pago.EstadoPago;
import com.styleshop.model.Pedido;
import com.styleshop.repository.PagoRepository;
import com.styleshop.repository.PedidoRepository;
import com.styleshop.service.PagoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PagoServiceImpl implements PagoService {

    private final PagoRepository pagoRepository;
    private final PedidoRepository pedidoRepository;

    @Override
    @Transactional
    public PagoResponse registrarPago(RegistrarPagoRequest request) {
        Pedido pedido = pedidoRepository.findById(request.getIdPedido())
                .orElseThrow(() -> new RuntimeException(
                        "Pedido no encontrado con ID: " + request.getIdPedido()));

        if (pagoRepository.existsByPedido_IdPedido(request.getIdPedido())) {
            throw new RuntimeException(
                    "El pedido con ID " + request.getIdPedido() + " ya tiene un pago registrado");
        }

        String metodo = request.getMetodoPago().toUpperCase();
        if (!metodo.equals("TARJETA") && !metodo.equals("SINPE") && !metodo.equals("EFECTIVO")) {
            throw new RuntimeException(
                    "Método de pago no válido. Use: TARJETA, SINPE o EFECTIVO");
        }

        String referencia = "SS-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();

        Pago pago = Pago.builder()
                .pedido(pedido)
                .metodoPago(metodo)
                .monto(request.getMonto())
                .estado(EstadoPago.APROBADO)
                .referencia(referencia)
                .build();

        Pago pagoGuardado = pagoRepository.save(pago);

        return mapToResponse(pagoGuardado, "Pago registrado exitosamente (simulado)");
    }

    @Override
    @Transactional(readOnly = true)
    public PagoResponse obtenerPagoPorPedido(Long idPedido) {
        Pago pago = pagoRepository.findByPedido_IdPedido(idPedido)
                .orElseThrow(() -> new RuntimeException(
                        "No existe pago registrado para el pedido con ID: " + idPedido));
        return mapToResponse(pago, "Pago encontrado");
    }

    private PagoResponse mapToResponse(Pago pago, String mensaje) {
        return PagoResponse.builder()
                .idPago(pago.getIdPago())
                .idPedido(pago.getPedido().getIdPedido())
                .metodoPago(pago.getMetodoPago())
                .monto(pago.getMonto())
                .fechaPago(pago.getFechaPago())
                .estado(pago.getEstado())
                .referencia(pago.getReferencia())
                .mensaje(mensaje)
                .build();
    }
}