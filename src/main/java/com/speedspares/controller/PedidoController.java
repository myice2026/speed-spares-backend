package com.speedspares.controller;

import com.speedspares.model.Pedido;
import com.speedspares.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Map;
import java.util.List;

@RestController
@RequestMapping("/api/pedidos")
@CrossOrigin(origins = "*")
public class PedidoController {

    @Autowired private PedidoService service;

    // Endpoint para comprar: POST /api/pedidos/comprar
    @PostMapping("/comprar")
    public ResponseEntity<?> comprar(@RequestBody Map<String, Long> datos) {
        Long usuarioId = datos.get("usuarioId");
        Long productoId = datos.get("productoId");
        
        try {
            Pedido pedido = service.crearPedido(usuarioId, productoId);
            return ResponseEntity.ok(pedido);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error al procesar pedido");
        }
    }
    
    // Ver mis compras
    @GetMapping("/usuario/{id}")
    public List<Pedido> misPedidos(@PathVariable Long id) {
        return service.listarPorUsuario(id);
    }
}