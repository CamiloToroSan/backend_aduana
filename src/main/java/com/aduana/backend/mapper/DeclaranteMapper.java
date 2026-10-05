package com.aduana.backend.mapper;

import com.aduana.backend.domain.actor.Declarante;
import com.aduana.backend.dto.request.CrearDeclaranteRequest;
import com.aduana.backend.dto.response.DeclaranteResponse;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface DeclaranteMapper {

    DeclaranteResponse toResponse(Declarante declarante);

    List<DeclaranteResponse> toResponseList(List<Declarante> declarantes);

    default Declarante toEntity(CrearDeclaranteRequest request) {
        return new Declarante(request.ruc(), request.nombre(), request.direccion());
    }
}