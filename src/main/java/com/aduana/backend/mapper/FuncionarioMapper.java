package com.aduana.backend.mapper;

import com.aduana.backend.domain.actor.Funcionario;
import com.aduana.backend.dto.request.CrearFuncionarioRequest;
import com.aduana.backend.dto.response.FuncionarioResponse;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface FuncionarioMapper {

    FuncionarioResponse toResponse(Funcionario funcionario);

    List<FuncionarioResponse> toResponseList(List<Funcionario> funcionarios);

    default Funcionario toEntity(CrearFuncionarioRequest request) {
        return new Funcionario(request.nombre(), request.cargo(), request.turno());
    }
}