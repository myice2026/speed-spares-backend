package com.speedspares.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "pedidos")
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime fecha;
    private String estado; // "PENDIENTE", "ENVIADO"

    // Relación: Muchos pedidos pueden ser de Un usuario
    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    // Relación: Muchos pedidos pueden contener Un producto (simplificado)
    @ManyToOne
    @JoinColumn(name = "producto_id")
    private Producto producto;

    public Pedido() {
        this.fecha = LocalDateTime.now();
        this.estado = "PENDIENTE";
    }

    public Pedido(Usuario usuario, Producto producto) {
        this.fecha = LocalDateTime.now();
        this.estado = "PENDIENTE";
        this.usuario = usuario;
        this.producto = producto;
    }

    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public LocalDateTime getFecha() { return fecha; }
    public void setFecha(LocalDateTime fecha) { this.fecha = fecha; }
    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
    public Usuario getUsuario() { return usuario; }
    public void setUsuario(Usuario usuario) { this.usuario = usuario; }
    public Producto getProducto() { return producto; }
    public void setProducto(Producto producto) { this.producto = producto; }
}