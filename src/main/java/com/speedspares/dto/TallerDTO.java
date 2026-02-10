package com.speedspares.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TallerDTO {
    private Long id;
    private String nombre;
    private String direccion;
    private String telefono;
    private Double latitud;
    private Double longitud;
    private String imagenUrl;
}
