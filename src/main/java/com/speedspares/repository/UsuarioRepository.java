package com.speedspares.repository;

import com.speedspares.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    // Método mágico: Spring crea el SQL automáticamente al leer "findByEmail"
    Optional<Usuario> findByEmail(String email);
}