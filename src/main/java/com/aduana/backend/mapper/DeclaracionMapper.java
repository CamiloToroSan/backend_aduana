package com.aduana.backend.mapper;

import com.aduana.backend.domain.declaracion.Declaracion;
import com.aduana.backend.domain.declaracion.Mercancia;
import com.aduana.backend.dto.response.DeclaracionDetalleResponse;
import com.aduana.backend.dto.response.MercanciaItemResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring")
public interface DeclaracionMapper {

    @Mapping(target = "declaranteNombre", source = "declarante.nombre")
    @Mapping(target = "declaranteRuc", source = "declarante.ruc")
    @Mapping(target = "funcionarioNombre", source = "funcionarioAsignado.nombre")
    @Mapping(target = "regimen", expression = "java(declaracion.getRegimen().name())")
    @Mapping(target = "estado", expression = "java(declaracion.getEstado().name())")
    @Mapping(target = "totalTributos",
            expression = "java(declaracion.calcularTributos().monto())")
    @Mapping(target = "monedaTributos",
            expression = "java(declaracion.calcularTributos().moneda())")
    @Mapping(target = "mercancias", source = "mercancias", qualifiedByName = "toMercanciaList")
    DeclaracionDetalleResponse toDetalle(Declaracion declaracion);

    @Named("toMercanciaList")
    default List<MercanciaItemResponse> toMercanciaList(List<Mercancia> mercancias) {
        return mercancias.stream()
                .map(this::toMercanciaItem)
                .toList();
    }

    @Mapping(target = "paisOrigen", source = "origen.codigo")
    @Mapping(target = "paisDestino", source = "destino.codigo")
    @Mapping(target = "valorAduana", source = "valorAduana.monto")
    @Mapping(target = "moneda", source = "valorAduana.moneda")
    @Mapping(target = "pesoNeto", source = "pesoNeto.kg")
    @Mapping(target = "pesoBruto", source = "pesoBruto.kg")
    MercanciaItemResponse toMercanciaItem(Mercancia mercancia);
}