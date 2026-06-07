package com.styleshop.service;

import com.styleshop.model.Categoria;
import com.styleshop.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class CategoriaService {
    @Autowired
    private CategoriaRepository categoriaRepository;

    //lista de todas las categorias
    public List<Categoria> listarTodas() {
        return categoriaRepository.findAll();
    }

    //crear una categoria nueva
    public Categoria created(Categoria categoria) {
        return categoriaRepository.save(categoria);
    }

    //editar una categoria existente
    public Categoria edit(Long id, Categoria datos) {
        Optional<Categoria> opt = categoriaRepository.findById(id);
        if (opt.isEmpty()) {
            throw new RuntimeException("Categoria no encontrada");

        }
        Categoria categoria = opt.get();
        categoria.setNombre(datos.getNombre());
        categoria.setDescripcion(datos.getDescripcion());
        return categoriaRepository.save(categoria);

    }

    //eliminar una categoria
    public void delete(Long id) {
        if (!categoriaRepository.existsById(id)) {
            throw new RuntimeException("Categoria no encontrada");
        }
        categoriaRepository.deleteById(id);


    }


}
