package com.styleshop.service;

import com.styleshop.model.Usuario;
import com.styleshop.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    //Registro de nuevo cliente
    public Usuario registrarCliente(Usuario usuario) {
        usuario.setRol("CLIENTE");
        usuario.setActivo(true);
        return usuarioRepository.save(usuario);
    }

    //Ver el perfil de algun usuario
    public Usuario verPerfil(Long idUsuario){
        return usuarioRepository.findById(idUsuario)
            .orElseThrow(() ->new RuntimeException("Usuario no encontrado"));
    }

    //Lista para todos los usuarios pero solo para admin
    public List<Usuario> listarUsuarios(){
        return usuarioRepository.findAll();
    }
}