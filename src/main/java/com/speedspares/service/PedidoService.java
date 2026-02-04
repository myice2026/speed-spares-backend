package com.speedspares.service;

import com.speedspares.model.Pedido;
import com.speedspares.model.Producto;
import com.speedspares.model.Usuario;
import com.speedspares.repository.PedidoRepository;
import com.speedspares.repository.ProductoRepository;
import com.speedspares.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PedidoService {
    @Autowired private PedidoRepository pedidoRepo;
    @Autowired private UsuarioRepository usuarioRepo;
    @Autowired private ProductoRepository productoRepo;

    public Pedido crearPedido(Long usuarioId, Long productoId) {
        Usuario u = usuarioRepo.findById(usuarioId).orElseThrow();
        Producto p = productoRepo.findById(productoId).orElseThrow();
        
        Pedido nuevo = new Pedido(u, p);
        return pedidoRepo.save(nuevo);
    }

    public List<Pedido> listarPorUsuario(Long usuarioId) {
        return pedidoRepo.findByUsuarioId(usuarioId);
    }
}