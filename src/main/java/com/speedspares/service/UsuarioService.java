package com.speedspares.service;

import com.speedspares.dto.UsuarioDTO;
import com.speedspares.mapper.UsuarioMapper;
import com.speedspares.model.entity.Usuario;
import com.speedspares.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository repository;

    @Autowired
    private UsuarioMapper mapper;

    public List<UsuarioDTO> obtenerTodos() {
        return repository.findAll().stream()
                .map(mapper::toDTO)
                .collect(Collectors.toList());
    }

    public UsuarioDTO obtenerPorId(Long id) {
        Usuario usuario = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        return mapper.toDTO(usuario);
    }
}