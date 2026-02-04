package com.speedspares.controller;

import com.speedspares.model.Taller;
import com.speedspares.service.TallerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/talleres")
@CrossOrigin(origins = "*")
public class TallerController {
    @Autowired
    private TallerService service;

    @GetMapping
    public List<Taller> listar() {
        return service.listarTalleres();
    }
}