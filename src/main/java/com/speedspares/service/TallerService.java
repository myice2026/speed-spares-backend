package com.speedspares.service;

import com.speedspares.dto.TallerDTO;
import com.speedspares.mapper.TallerMapper;
import com.speedspares.repository.TallerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TallerService {

    @Autowired
    private TallerRepository repository;

    @Autowired
    private TallerMapper mapper;

    public List<TallerDTO> obtenerTodos() {
        return repository.findAll().stream()
                .map(mapper::toDTO)
                .collect(Collectors.toList());
    }
}