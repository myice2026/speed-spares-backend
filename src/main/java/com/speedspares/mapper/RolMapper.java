package com.speedspares.mapper;

import com.speedspares.dto.RolDTO;
import com.speedspares.model.entity.Rol;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RolMapper {
    RolDTO toDTO(Rol rol);

    Rol toEntity(RolDTO rolDTO);
}
