package com.speedspares.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductoDTO {
    private Long id;
    private String nombre;
    private String descripcion;
    private Double precio;
    private String sku;
    private Integer cantidadStock;
    private String imagenPrincipalUrl;
    private CategoriaDTO categoria;

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Request {
        @NotBlank(message = "El nombre del producto es obligatorio")
        private String nombre;

        private String descripcion;
        private String descripcionLarga;

        @NotNull(message = "El precio es obligatorio")
        @Positive(message = "El precio debe ser mayor a 0")
        private Double precio;

        private String sku;
        private Integer cantidadStock;
        private String imagenPrincipalUrl;
        private Long categoriaId;
    }
}
