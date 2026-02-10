package com.speedspares.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "direcciones")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Direccion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    private String nombreDestinatario;
    private String callePrincipal;
    private String numeroExterior;
    private String informacionAdicional;
    private String ciudad;
    private String estadoProvincia;
    private String codigoPostal;
    private String pais;

    private Boolean esPredeterminadaEnvio;
    private Boolean esPredeterminadaFacturacion;
}
