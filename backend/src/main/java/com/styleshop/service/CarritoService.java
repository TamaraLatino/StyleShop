package com.styleshop.service;

import com.styleshop.model.Carrito;
import com.styleshop.model.CarritoDetalle;
import com.styleshop.model.Inventario;
import com.styleshop.repository.CarritoDetalleRepository;
import com.styleshop.repository.CarritoRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;

public class CarritoService {

    @Autowired
    private CarritoRepository carritoRepository;

    @Autowired
    private CarritoDetalleRepository carritoDetalleRepository;

    //Ver carrito del usuario
    public Carrito verCarrito(Long idCarrito){
        return carritoRepository.findById(idCarrito)
                .orElseThrow(()->new RuntimeException("Carrito no encontrado"));
    }
    //Agregar producto al carrito
    public CarritoDetalle agregarProducto(Long idCarrito, Long idInventario, Integer cantidad, BigDecimal precioUnitario){
        Carrito carrito = carritoRepository.findById(idCarrito)
                .orElseThrow(()-> new RuntimeException("Carrito no encontrado"));

        Inventario inventario = new Inventario();
        inventario.setIdInventario(idInventario);

        CarritoDetalle detalle = new CarritoDetalle();
        detalle.setCarrito(carrito);
        detalle.setInventario(inventario);
        detalle.setCantidad(cantidad);
        detalle.setPrecioUnitario(precioUnitario);

        return carritoDetalleRepository.save(detalle);
    }

    //Es para eliminar el producto de carrito
    public void eliminarProducto(Long idCarritoDetalle){
        carritoDetalleRepository.deleteById(idCarritoDetalle);
    }
}
