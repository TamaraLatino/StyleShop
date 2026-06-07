package com.styleshop.service;

import com.styleshop.model.Direccion;
import com.styleshop.repository.DireccionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class DireccionService {

    @Autowired
    private DireccionRepository direccionRepository; // ← era direccionService, nombre confuso

    // Agregar dirección
    public Direccion agregarDireccion(Direccion direccion) {
        return direccionRepository.save(direccion);
    }

    // Ver direcciones de un cliente
    public List<Direccion> verDirecciones(Long idUsuario) {
        return direccionRepository.findByUsuario_IdUsuario(idUsuario);
    }

    // Eliminar dirección
    public void eliminarDireccion(Long idDireccion) {
        direccionRepository.deleteById(idDireccion);
    }
}