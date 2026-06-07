package com.styleshop.service;

import com.styleshop.model.Producto;
import com.styleshop.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    //lista productos
    public List<Producto> listarTodos() {
        return productoRepository.findAll();
    }

    //Crear un producto
    public Producto created(Producto producto) {
        return productoRepository.save(producto);
    }

    //ver un producto especifico por su Id
    public Producto identifyById(Long id) {
        Optional<Producto> opt = productoRepository.findById(id);
        if (opt.isEmpty()) {
            throw new RuntimeException("Producto no encontrado");
        }
        return opt.get();
    }

    //editar producto que ya existe
    public Producto edit(Long id, Producto datos) {
        Optional<Producto> opt = productoRepository.findById(id);
        if (opt.isEmpty()) {
            throw new RuntimeException("Producto no encontrado");
        }
        Producto producto = opt.get();
        producto.setNombre(datos.getNombre());
        producto.setDescripcion(datos.getDescripcion());
        producto.setPrecio(datos.getPrecio());
        producto.setDestacado(datos.getDestacado());
        producto.setActivo(datos.getActivo());
        producto.setCategoria(datos.getCategoria());
        return productoRepository.save(producto);
    }

    // Eliminar un producto
    public void delete(Long id) {
        if (!productoRepository.existsById(id)) {
            throw new RuntimeException("Producto no encontrado");
        }
        productoRepository.deleteById(id);
    }

    // Listar productos por categoria
    public List<Producto> listarPorCategoria(Long idCategoria) {
        return productoRepository.findByCategoriaIdCategoria(idCategoria);
    }
}
