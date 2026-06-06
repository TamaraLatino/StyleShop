package com.styleshop.repository;

import com.styleshop.model.Pago;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PagoRepository extends JpaRepository<Pago, Long> {

    Optional<Pago> findByPedido_IdPedido(Long idPedido);

    boolean existsByPedido_IdPedido(Long idPedido);
}