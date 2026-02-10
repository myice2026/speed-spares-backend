package com.speedspares.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PedidoDTO {
    private Long id;
    private LocalDateTime fecha;
    private String estado;
    private Double total;
    private Long usuarioId;

    private Double subtotal;
    private Double costoEnvio;
    private Double impuestos;
    private String estadoPago;

    private Long direccionEnvioId;
    private Long direccionFacturacionId;
    private Long metodoPagoId;
    private Long estadoPedidoId;

    // Legacy fields validation
    private ProductoDTO producto;

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Request {
        @NotNull(message = "El usuario es obligatorio")
        private Long usuarioId;

        private Long productoId;
        private Integer cantidad;

        private Long direccionEnvioId;
        private Long direccionFacturacionId;
        private Long metodoPagoId;
    }
}
