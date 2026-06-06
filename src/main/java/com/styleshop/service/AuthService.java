package com.styleshop.service;

import com.styleshop.dto.LoginRequest;
import com.styleshop.dto.LoginResponse;
import com.styleshop.model.Usuario;
import com.styleshop.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class AuthService {

    //inyeccion de dependencias
    @Autowired
    private UsuarioRepository usuarioRepository;

    // declaracion del metodo login
    public LoginResponse login(LoginRequest request) {

        /* Se utiliza un tipo de dato Optional, es un tipo de
        dato especial, que guarda otro tipo de dato adentro
         Por eso tiene los < >*/

        Optional<Usuario> usuarioEncontrado = usuarioRepository.findByCorreo(request.getCorreo());
        // verifica que el usuario existe
        if (usuarioEncontrado.isEmpty()) {
            throw new RuntimeException("Correo no encontrado");
        }
        // sacar el usuario
        Usuario usuario = usuarioEncontrado.get();

        //Verifica que la contrasena es correcta
        //si la contraseña de la base de datos NO es igual a la que escribió el usuario, lanzá un error"
        if (!usuario.getContrasena().equals(request.getContrasena())) {
            throw new RuntimeException("Contraseña incorrecta");
        }

        //verifica que la cuenta esta activa
        if (!usuario.getActivo()) {
            throw new RuntimeException("Cuenta desactivada");
        }

        //genera un token unico
        /* que es eso: UUID.randomUUID().toString() lo que hace es que
        genera un código único aleatorio. UUID es una
        clase de Java que genera códigos como este:
         a3f4b2c1-8d7e-4f6a-b5c2-1234567890ab.
         Ese es el token— una llave única y difícil de adivinar.*/

        String token = UUID.randomUUID().toString();

        //retornar la repuesta con el token , rol y nombre
        return new LoginResponse(token, usuario.getRol(), usuario.getNombre());

    }

    // registrar un cliente nuevo
    public Usuario registro(Usuario nuevoUsuario) {

        //verifica que el correo no este ya registrado
        Optional<Usuario> existente = usuarioRepository.findByCorreo(nuevoUsuario.getCorreo());
        if (existente.isPresent()) {
            throw new RuntimeException("El correo ya esta registrado");
        }

        //asignar el rol de cliente y cuenta activa por defecto
        nuevoUsuario.setRol("cliente");
        nuevoUsuario.setActivo(true);

        //guardar el usuario nuevo en la base de datos
        return usuarioRepository.save(nuevoUsuario);
    }

}
