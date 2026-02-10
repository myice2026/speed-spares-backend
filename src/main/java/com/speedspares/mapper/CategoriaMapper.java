package com.speedspares.mapper;

import com.speedspares.dto.CategoriaDTO;
import com.speedspares.model.entity.Categoria;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CategoriaMapper {
    CategoriaDTO toDTO(Categoria categoria);

    Categoria toEntity(CategoriaDTO categoriaDTO);
}
