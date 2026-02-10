package com.speedspares.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "carrito_compra")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Carrito {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_usuario", unique = true)
    private Usuario usuario;

    @Builder.Default
    private LocalDateTime fechaCreacion = LocalDateTime.now();

    @Builder.Default
    private LocalDateTime fechaUltimaModificacion = LocalDateTime.now();

    @OneToMany(mappedBy = "carrito", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ItemCarrito> items;
}
