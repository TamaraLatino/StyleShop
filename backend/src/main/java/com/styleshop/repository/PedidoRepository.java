package com.styleshop.repository;

import com.styleshop.model.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {
    List<Pedido> findByUsuarioIdUsuario(Long idUsuario);

    List<Pedido> findByEstado(String estado);

    List<Pedido> findByFechaPedidoBetween(LocalDateTime desde, LocalDateTime hasta);

    List<Pedido> findByEstadoAndFechaPedidoBetween(String estado, LocalDateTime desde, LocalDateTime hasta);
}