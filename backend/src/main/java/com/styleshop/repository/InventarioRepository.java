package com.styleshop.repository;

import com.styleshop.model.Inventario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface InventarioRepository extends JpaRepository<Inventario, Long> {

    Optional<Inventario> findByProducto_IdProductoAndTallaAndColor(
            Long idProducto, String talla, String color);

    List<Inventario> findByProducto_IdProducto(Long idProducto);

    List<Inventario> findByStockBajoTrue();

    @Query("SELECT i FROM Inventario i WHERE i.producto.idProducto = :idProducto AND i.stockBajo = true")
    List<Inventario> findStockBajoByProducto(@Param("idProducto") Long idProducto);

    boolean existsByProducto_IdProductoAndTallaAndColor(
            Long idProducto, String talla, String color);
}