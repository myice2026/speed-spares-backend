package com.speedspares.mapper;

import com.speedspares.dto.TallerDTO;
import com.speedspares.model.entity.Taller;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TallerMapper {
    TallerDTO toDTO(Taller taller);

    @Mapping(target = "id", ignore = true)
    Taller toEntity(TallerDTO dto);
}
