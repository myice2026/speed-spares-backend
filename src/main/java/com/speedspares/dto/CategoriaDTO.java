package com.speedspares.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CategoriaDTO {
    private Long id;
    private String nombre;
    private String descripcion;
}
