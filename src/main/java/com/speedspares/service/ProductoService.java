package com.speedspares.service; // <--- CORREGIDO

import com.speedspares.model.Producto; // <--- CORREGIDO
import com.speedspares.repository.ProductoRepository; // <--- CORREGIDO
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ProductoService {

    @Autowired
    private ProductoRepository repositorio;

    public List<Producto> obtenerTodos() {
        return repositorio.findAll();
    }

    public Producto guardar(Producto producto) {
        return repositorio.save(producto);
    }
}