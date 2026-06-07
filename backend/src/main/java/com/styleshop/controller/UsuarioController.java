package com.styleshop.controller;

import com.styleshop.model.Usuario;
import com.styleshop.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    //Registrar cliente nuevo
    @PostMapping("/registro")
    public ResponseEntity<Usuario> registrar(@RequestBody Usuario usuario){
        return ResponseEntity.ok(usuarioService.registrarCliente(usuario));
    }

    //Ver el perfil
    @GetMapping("/{idUsuario}")
    public ResponseEntity<Usuario> verPerfil(@PathVariable Long isUsuario){
        return ResponseEntity.ok(usuarioService.verPerfil(isUsuario));
    }

    //Listar los usuarios pero solo los admin
    @GetMapping("/admin/todos")
    public ResponseEntity<List<Usuario>> listarTodos() {
        return ResponseEntity.ok(usuarioService.listarUsuarios());
    }
}
