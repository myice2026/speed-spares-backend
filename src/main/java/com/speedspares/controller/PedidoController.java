package com.speedspares.controller;

import com.speedspares.dto.PedidoDTO;
import com.speedspares.service.PedidoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {

    @Autowired
    private PedidoService service;

    @GetMapping
    public ResponseEntity<List<PedidoDTO>> misPedidos() {
        return ResponseEntity.ok(service.obtenerMisPedidos());
    }

    @PostMapping
    public ResponseEntity<PedidoDTO> crear(@Valid @RequestBody PedidoDTO.Request request) {
        return ResponseEntity.ok(service.crearPedido(request));
    }
}