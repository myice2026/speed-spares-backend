package com.speedspares.mapper;

import com.speedspares.dto.ProductoDTO;
import com.speedspares.dto.CategoriaDTO;
import com.speedspares.model.entity.Producto;
import com.speedspares.model.entity.Categoria;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProductoMapper {

    ProductoDTO toDTO(Producto producto);

    CategoriaDTO toDTO(Categoria categoria);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "fechaCreacion", expression = "java(java.time.LocalDateTime.now())")
    @Mapping(target = "fechaActualizacion", ignore = true)
    @Mapping(target = "activo", constant = "true")
    @Mapping(target = "categoria", ignore = true) // Set in Service
    Producto toEntity(ProductoDTO.Request request);
}
