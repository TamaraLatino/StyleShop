package com.styleshop.service.impl;

import com.styleshop.dto.InventarioDTO.ActualizarStockRequest;
import com.styleshop.dto.InventarioDTO.CrearInventarioRequest;
import com.styleshop.dto.InventarioDTO.InventarioResponse;
import com.styleshop.model.Inventario;
import com.styleshop.model.Producto;
import com.styleshop.repository.InventarioRepository;
import com.styleshop.repository.ProductoRepository;
import com.styleshop.service.InventarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class InventarioServiceImpl implements InventarioService {

    private final InventarioRepository inventarioRepository;
    private final ProductoRepository productoRepository;

    @Override
    @Transactional
    public InventarioResponse crearInventario(CrearInventarioRequest request) {
        Producto producto = productoRepository.findById(request.getIdProducto())
                .orElseThrow(() -> new RuntimeException(
                        "Producto no encontrado con ID: " + request.getIdProducto()));

        if (inventarioRepository.existsByProducto_IdProductoAndTallaAndColor(
                request.getIdProducto(), request.getTalla(), request.getColor())) {
            throw new RuntimeException(
                    "Ya existe inventario para ese producto con talla '"
                            + request.getTalla() + "' y color '" + request.getColor() + "'");
        }

        int stockMin = request.getStockMinimo() != null ? request.getStockMinimo() : 5;

        Inventario inventario = Inventario.builder()
                .producto(producto)
                .talla(request.getTalla())
                .color(request.getColor())
                .cantidadDisponible(request.getCantidadDisponible())
                .stockMinimo(stockMin)
                .stockBajo(request.getCantidadDisponible() < stockMin)
                .build();

        return mapToResponse(inventarioRepository.save(inventario));
    }

    @Override
    @Transactional(readOnly = true)
    public List<InventarioResponse> obtenerStockPorProducto(Long idProducto) {
        List<Inventario> lista = inventarioRepository.findByProducto_IdProducto(idProducto);
        if (lista.isEmpty()) {
            throw new RuntimeException(
                    "No hay inventario registrado para el producto con ID: " + idProducto);
        }
        return lista.stream().map(this::mapToResponse).collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public InventarioResponse obtenerStockPorTallaYColor(Long idProducto, String talla, String color) {
        Inventario inventario = inventarioRepository
                .findByProducto_IdProductoAndTallaAndColor(idProducto, talla, color)
                .orElseThrow(() -> new RuntimeException(
                        "No se encontró inventario para el producto " + idProducto
                                + " con talla '" + talla + "' y color '" + color + "'"));
        return mapToResponse(inventario);
    }

    @Override
    @Transactional
    public InventarioResponse actualizarStock(Long idInventario, ActualizarStockRequest request) {
        Inventario inventario = inventarioRepository.findById(idInventario)
                .orElseThrow(() -> new RuntimeException(
                        "Inventario no encontrado con ID: " + idInventario));

        inventario.setCantidadDisponible(request.getCantidadDisponible());

        if (request.getStockMinimo() != null) {
            inventario.setStockMinimo(request.getStockMinimo());
        }

        // *** ALERTA AUTOMÁTICA DE STOCK BAJO ***
        boolean alertaActiva = inventario.getCantidadDisponible() < inventario.getStockMinimo();
        inventario.setStockBajo(alertaActiva);

        return mapToResponse(inventarioRepository.save(inventario));
    }

    @Override
    @Transactional(readOnly = true)
    public List<InventarioResponse> obtenerProductosConStockBajo() {
        return inventarioRepository.findByStockBajoTrue()
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    private InventarioResponse mapToResponse(Inventario inv) {
        return InventarioResponse.builder()
                .idInventario(inv.getIdInventario())
                .idProducto(inv.getProducto().getIdProducto())
                .nombreProducto(inv.getProducto().getNombre())
                .talla(inv.getTalla())
                .color(inv.getColor())
                .cantidadDisponible(inv.getCantidadDisponible())
                .stockMinimo(inv.getStockMinimo())
                .stockBajo(inv.getStockBajo())
                .ultimaActualizacion(inv.getUltimaActualizacion())
                .build();
    }
}