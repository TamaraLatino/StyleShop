package com.styleshop.service;

import com.styleshop.dto.InventarioDTO.ActualizarStockRequest;
import com.styleshop.dto.InventarioDTO.CrearInventarioRequest;
import com.styleshop.dto.InventarioDTO.InventarioResponse;

import java.util.List;

public interface InventarioService {

    InventarioResponse crearInventario(CrearInventarioRequest request);

    List<InventarioResponse> obtenerStockPorProducto(Long idProducto);

    InventarioResponse obtenerStockPorTallaYColor(Long idProducto, String talla, String color);

    InventarioResponse actualizarStock(Long idInventario, ActualizarStockRequest request);

    List<InventarioResponse> obtenerProductosConStockBajo();
}