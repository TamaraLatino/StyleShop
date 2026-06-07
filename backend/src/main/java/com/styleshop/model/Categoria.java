package com.styleshop.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "categorias")
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCategoria;

    @Column(nullable = false, unique = true)
    private String nombre;

    private String descripcion;

    @Column(nullable = false)
    private Boolean activa = true;

    @OneToMany(mappedBy = "categoria")
    private List<Producto> productos;

}
