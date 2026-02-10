package com.speedspares.mapper;

import com.speedspares.dto.PedidoDTO;
import com.speedspares.model.entity.Pedido;
import com.speedspares.model.entity.EstadoPedido;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring", uses = { UsuarioMapper.class, ProductoMapper.class })
public interface PedidoMapper {

    @Mapping(target = "estado", source = "estado", qualifiedByName = "mapEstado")
    @Mapping(target = "usuarioId", source = "usuario.id")
    @Mapping(target = "direccionEnvioId", source = "direccionEnvio.id")
    @Mapping(target = "direccionFacturacionId", source = "direccionFacturacion.id")
    @Mapping(target = "metodoPagoId", source = "metodoPago.id")
    @Mapping(target = "estadoPedidoId", source = "estado.id")
    PedidoDTO toDTO(Pedido pedido);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "fecha", expression = "java(java.time.LocalDateTime.now())")
    @Mapping(target = "estado", ignore = true) // Set in Service
    @Mapping(target = "usuario", ignore = true) // Set in Service
    @Mapping(target = "producto", ignore = true)
    @Mapping(target = "direccionEnvio", ignore = true) // Set in Service
    @Mapping(target = "direccionFacturacion", ignore = true) // Set in Service
    @Mapping(target = "metodoPago", ignore = true) // Set in Service
    @Mapping(target = "subtotal", ignore = true) // Set in Service
    @Mapping(target = "costoEnvio", ignore = true) // Set in Service
    @Mapping(target = "impuestos", ignore = true) // Set in Service
    @Mapping(target = "montoTotal", ignore = true) // Set in Service
    @Mapping(target = "estadoPago", ignore = true) // Set in Service
    @Mapping(target = "referenciaPago", ignore = true) // Set in Service
    @Mapping(target = "observacionCliente", ignore = true)
    Pedido toEntity(PedidoDTO.Request request);

    @Named("mapEstado")
    default String mapEstado(EstadoPedido estado) {
        return estado != null ? estado.getNombre() : null;
    }
}
