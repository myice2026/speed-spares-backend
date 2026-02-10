package com.speedspares.service;

import com.speedspares.dto.RolDTO;
import com.speedspares.mapper.RolMapper;
import com.speedspares.repository.RolRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RolService {

    private final RolRepository repository;
    private final RolMapper mapper;

    public List<RolDTO> obtenerTodos() {
        return repository.findAll().stream()
                .map(mapper::toDTO)
                .collect(Collectors.toList());
    }
}
