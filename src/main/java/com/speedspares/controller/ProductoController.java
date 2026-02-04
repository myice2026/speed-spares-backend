package com.speedspares.controller; // <--- CORREGIDO

import com.speedspares.model.Producto; // <--- CORREGIDO
import com.speedspares.service.ProductoService; // <--- CORREGIDO
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/productos")
@CrossOrigin(origins = "*") 
public class ProductoController {

    @Autowired
    private ProductoService servicio;

    @GetMapping
    public List<Producto> listar() {
        return servicio.obtenerTodos();
    }

    @PostMapping
    public Producto crear(@RequestBody Producto producto) {
        return servicio.guardar(producto);
    }
}