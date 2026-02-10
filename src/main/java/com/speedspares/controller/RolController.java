package com.speedspares.controller;

import com.speedspares.dto.RolDTO;
import com.speedspares.service.RolService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/roles")
@RequiredArgsConstructor
public class RolController {

    private final RolService service;

    @GetMapping
    public ResponseEntity<List<RolDTO>> listar() {
        return ResponseEntity.ok(service.obtenerTodos());
    }
}
