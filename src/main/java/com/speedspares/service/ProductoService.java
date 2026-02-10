package com.speedspares.service;

import com.speedspares.dto.ProductoDTO;
import com.speedspares.mapper.ProductoMapper;
import com.speedspares.model.entity.Producto;
import com.speedspares.model.entity.Categoria;
import com.speedspares.repository.ProductoRepository;
import com.speedspares.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductoService {

    @Autowired
    private ProductoRepository repository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private ProductoMapper mapper;

    public List<ProductoDTO> obtenerTodos() {
        return repository.findAll().stream()
                .map(mapper::toDTO)
                .collect(Collectors.toList());
    }

    public ProductoDTO guardar(ProductoDTO.Request request) {
        Producto producto = mapper.toEntity(request);

        if (request.getCategoriaId() != null) {
            Categoria categoria = categoriaRepository.findById(request.getCategoriaId())
                    .orElseThrow(() -> new RuntimeException("Categoría no encontrada"));
            producto.setCategoria(categoria);
        }

        return mapper.toDTO(repository.save(producto));
    }

    public ProductoDTO obtenerPorId(Long id) {
        Producto producto = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));
        return mapper.toDTO(producto);
    }
}