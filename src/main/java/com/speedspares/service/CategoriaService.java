package com.speedspares.service;

import com.speedspares.dto.CategoriaDTO;
import com.speedspares.mapper.CategoriaMapper;
import com.speedspares.model.entity.Categoria;
import com.speedspares.repository.CategoriaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoriaService {

    private final CategoriaRepository repository;
    private final CategoriaMapper mapper;

    public List<CategoriaDTO> obtenerTodas() {
        return repository.findAll().stream()
                .map(mapper::toDTO)
                .collect(Collectors.toList());
    }

    public CategoriaDTO obtenerPorId(Long id) {
        Categoria categoria = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Categoría no encontrada"));
        return mapper.toDTO(categoria);
    }

    @Transactional
    public CategoriaDTO guardar(CategoriaDTO dto) {
        Categoria categoria = mapper.toEntity(dto);
        return mapper.toDTO(repository.save(categoria));
    }
}
