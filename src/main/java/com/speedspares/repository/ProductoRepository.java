package com.speedspares.repository; // <--- CORREGIDO

import com.speedspares.model.Producto; // <--- CORREGIDO
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long> {
}