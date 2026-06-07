package com.styleshop.service;

import com.styleshop.dto.PagoDTO.PagoResponse;
import com.styleshop.dto.PagoDTO.RegistrarPagoRequest;

public interface PagoService {

    PagoResponse registrarPago(RegistrarPagoRequest request);

    PagoResponse obtenerPagoPorPedido(Long idPedido);
}