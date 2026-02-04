package com.speedspares.service;

import com.speedspares.model.Usuario;
import com.speedspares.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository repository;

    // Registrar nuevo usuario
    public Usuario registrar(Usuario usuario) {
        return repository.save(usuario);
    }

    // Validar Login
    public Usuario login(String email, String password) {
        Optional<Usuario> usuario = repository.findByEmail(email);
        
        // Si el usuario existe Y la contraseña coincide
        if (usuario.isPresent() && usuario.get().getPassword().equals(password)) {
            return usuario.get();
        }
        return null; // Login fallido
    }
}