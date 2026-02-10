package com.speedspares.service;

import com.speedspares.dto.PedidoDTO;
import com.speedspares.mapper.PedidoMapper;
import com.speedspares.model.entity.*; // Importing all entities
import com.speedspares.repository.*; // Importing all repositories
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PedidoService {

        @Autowired
        private PedidoRepository pedidoRepository;
        @Autowired
        private UsuarioRepository usuarioRepository;
        @Autowired
        private ProductoRepository productoRepository;
        @Autowired
        private DireccionRepository direccionRepository;
        @Autowired
        private MetodoPagoRepository metodoPagoRepository;
        @Autowired
        private EstadoPedidoRepository estadoPedidoRepository;
        @Autowired
        private PedidoMapper mapper;

        public List<PedidoDTO> obtenerMisPedidos() {
                String email = SecurityContextHolder.getContext().getAuthentication().getName();
                Usuario usuario = usuarioRepository.findByEmail(email)
                                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

                return pedidoRepository.findByUsuarioId(usuario.getId()).stream()
                                .map(mapper::toDTO)
                                .collect(Collectors.toList());
        }

        @Transactional
        public PedidoDTO crearPedido(PedidoDTO.Request request) {
                String email = SecurityContextHolder.getContext().getAuthentication().getName();
                Usuario usuario = usuarioRepository.findByEmail(email)
                                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

                // Load related entities
                // Note: For now we handle single product creation for backward compatibility
                // But ideally request should have a list of items or cart reference.
                // Assuming request has IDs for lookup tables which are mandatory now

                EstadoPedido estado = estadoPedidoRepository.findById(1L) // Default or find by name 'Pendiente'
                                .or(() -> estadoPedidoRepository.findByNombre("Pendiente de Pago"))
                                .or(() -> estadoPedidoRepository.findById(request.getMetodoPagoId())) // Fallback? No.
                                .orElseThrow(() -> new RuntimeException(
                                                "Estado de pedido inicial no encontrado (Pendiente)"));

                Direccion envio = direccionRepository.findById(request.getDireccionEnvioId())
                                .orElseThrow(() -> new RuntimeException("Dirección de envío no encontrada"));

                Direccion facturacion = direccionRepository.findById(request.getDireccionFacturacionId())
                                .orElseThrow(() -> new RuntimeException("Dirección de facturación no encontrada"));

                MetodoPago metodoPago = metodoPagoRepository.findById(request.getMetodoPagoId())
                                .orElseThrow(() -> new RuntimeException("Método de pago no encontrado"));

                Producto producto = null;
                if (request.getProductoId() != null) {
                        producto = productoRepository.findById(request.getProductoId())
                                        .orElseThrow(() -> new RuntimeException("Producto no encontrado"));
                }

                Pedido pedido = Pedido.builder()
                                .usuario(usuario)
                                .producto(producto) // Legacy field
                                .estado(estado)
                                .direccionEnvio(envio)
                                .direccionFacturacion(facturacion)
                                .metodoPago(metodoPago)
                                .fecha(java.time.LocalDateTime.now())
                                .subtotal(producto != null ? producto.getPrecio()
                                                * (request.getCantidad() != null ? request.getCantidad() : 1) : 0.0)
                                .costoEnvio(6000.0)
                                .impuestos(0.0)
                                .montoTotal(0.0) // Calculate sum
                                .build();

                pedido.setMontoTotal(pedido.getSubtotal() + pedido.getCostoEnvio() + pedido.getImpuestos());

                return mapper.toDTO(pedidoRepository.save(pedido));
        }
}