package com.speedspares.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "pedidos")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Builder.Default
    private LocalDateTime fecha = LocalDateTime.now();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    // Relaciones nuevas
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_estado_pedido", nullable = false)
    private EstadoPedido estado;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_metodo_pago", nullable = false)
    private MetodoPago metodoPago;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_direccion_envio", nullable = false)
    private Direccion direccionEnvio;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_direccion_facturacion", nullable = false)
    private Direccion direccionFacturacion;

    private Double subtotal;
    private Double costoEnvio;
    private Double impuestos;
    private Double montoTotal;
    private String observacionCliente;
    private String estadoPago; // 'Pendiente', 'Pagado'
    private String referenciaPago;

    // TODO: Relación OneToMany con DetallePedido (pendiente implementar
    // DetallePedido si no existe)
    // Por ahora mantengo compatibilidad con el código anterior que usaba Producto
    // directo
    // Pero idealmente esto cambia a DetallePedido.
    @Transient // Marco como transient para que no persista y no rompa mientras migramos lógica
    private Producto producto;
}