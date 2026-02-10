package com.speedspares.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UsuarioDTO {
    private Long id;
    private String nombreCompleto;
    private String email;
    private String telefono;
    private Boolean activo;
    private java.util.Set<String> roles; // Send role names
}
