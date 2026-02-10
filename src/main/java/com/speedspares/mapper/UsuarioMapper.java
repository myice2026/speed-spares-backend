package com.speedspares.mapper;

import com.speedspares.dto.UsuarioDTO;
import com.speedspares.dto.AuthDTO;
import com.speedspares.model.entity.Usuario;
import com.speedspares.model.entity.Rol;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {

    @Mapping(target = "roles", source = "roles", qualifiedByName = "mapRoles")
    UsuarioDTO toDTO(Usuario usuario);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "password", ignore = true)
    @Mapping(target = "roles", ignore = true)
    @Mapping(target = "direcciones", ignore = true)
    @Mapping(target = "fechaRegistro", ignore = true)
    @Mapping(target = "activo", ignore = true)
    Usuario toEntity(UsuarioDTO usuarioDTO);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "password", ignore = true)
    @Mapping(target = "roles", ignore = true)
    @Mapping(target = "direcciones", ignore = true)
    @Mapping(target = "activo", constant = "true")
    @Mapping(target = "fechaRegistro", expression = "java(java.time.LocalDateTime.now())")
    @Mapping(target = "telefono", source = "telefono") // Explicitly mapping phone
    Usuario toEntity(AuthDTO.RegisterRequest request);

    @Named("mapRoles")
    default Set<String> mapRoles(Set<Rol> roles) {
        if (roles == null)
            return null;
        return roles.stream().map(Rol::getNombre).collect(Collectors.toSet());
    }
}
