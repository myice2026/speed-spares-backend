package com.speedspares.controller;

import com.speedspares.dto.TallerDTO;
import com.speedspares.service.TallerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/talleres")
public class TallerController {

    @Autowired
    private TallerService service;

    @GetMapping
    public ResponseEntity<List<TallerDTO>> listar() {
        return ResponseEntity.ok(service.obtenerTodos());
    }
}