package com.styleshop.service;

import com.styleshop.dto.PedidoDTO;
import com.styleshop.model.*;
import com.styleshop.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private PedidoDetalleRepository pedidoDetalleRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private DireccionRepository direccionRepository;

    @Autowired
    private InventarioRepository inventarioRepository;

    // Crear pedido
    public Pedido crearPedido(PedidoDTO dto) {

        Usuario usuario = usuarioRepository.findById(dto.getIdUsuario())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        Direccion direccion = direccionRepository.findById(dto.getIdDireccion())
                .orElseThrow(() -> new RuntimeException("Dirección no encontrada"));

        Pedido pedido = new Pedido();
        pedido.setUsuario(usuario);
        pedido.setDireccion(direccion);
        pedido.setFechaPedido(LocalDateTime.now());
        pedido.setEstado("PENDIENTE");
        pedido.setNotas(dto.getNotas());
        pedido.setTotal(BigDecimal.ZERO);
        pedidoRepository.save(pedido);

        BigDecimal total = BigDecimal.ZERO;

        for (PedidoDTO.DetallePedidoDTO item : dto.getDetalles()) {

            Inventario inventario = inventarioRepository.findById(item.getIdInventario())
                    .orElseThrow(() -> new RuntimeException("Inventario no encontrado"));

            if (inventario.getCantidadDisponible() < item.getCantidad()) {
                throw new RuntimeException("Stock insuficiente: " + inventario.getProducto().getNombre());
            }

            PedidoDetalle detalle = new PedidoDetalle();
            detalle.setPedido(pedido);
            detalle.setInventario(inventario);
            detalle.setCantidad(item.getCantidad());
            detalle.setPrecioUnitario(item.getPrecioUnitario());
            detalle.setSubtotal(item.getPrecioUnitario().multiply(BigDecimal.valueOf(item.getCantidad())));
            pedidoDetalleRepository.save(detalle);

            inventario.setCantidadDisponible(inventario.getCantidadDisponible() - item.getCantidad());
            inventarioRepository.save(inventario);
            total = total.add(detalle.getSubtotal());
        }

        pedido.setTotal(total);
        return pedidoRepository.save(pedido);
    }

    // Listar pedidos de un cliente
    public List<Pedido> listarPedidos(Long idUsuario) {
        return pedidoRepository.findByUsuarioIdUsuario(idUsuario);
    }

    // Ver un pedido por ID
    public Pedido obtenerPedido(Long idPedido) {
        return pedidoRepository.findById(idPedido)
                .orElseThrow(() -> new RuntimeException("Pedido no encontrado"));
    }

    // Cambiar estado del pedido
    public Pedido cambiarEstado(Long idPedido, String nuevoEstado) {
        Pedido pedido = obtenerPedido(idPedido);
        pedido.setEstado(nuevoEstado);
        return pedidoRepository.save(pedido);
    }

    // Reporte para admin
    public List<Pedido> reportePedidos(String estado, LocalDateTime desde, LocalDateTime hasta) {
        if (estado != null && desde != null && hasta != null) {
            return pedidoRepository.findByEstadoAndFechaPedidoBetween(estado, desde, hasta);
        } else if (estado != null) {
            return pedidoRepository.findByEstado(estado);
        } else if (desde != null && hasta != null) {
            return pedidoRepository.findByFechaPedidoBetween(desde, hasta);
        }
        return pedidoRepository.findAll();
    }
}