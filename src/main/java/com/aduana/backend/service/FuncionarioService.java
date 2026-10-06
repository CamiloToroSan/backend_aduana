package com.aduana.backend.service;

import com.aduana.backend.domain.actor.Funcionario;
import com.aduana.backend.dto.request.CrearFuncionarioRequest;
import com.aduana.backend.dto.response.FuncionarioResponse;
import com.aduana.backend.mapper.FuncionarioMapper;
import com.aduana.backend.repository.FuncionarioRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class FuncionarioService {

    private final FuncionarioRepository funcionarioRepository;
    private final FuncionarioMapper funcionarioMapper;

    public FuncionarioService(FuncionarioRepository funcionarioRepository,
                              FuncionarioMapper funcionarioMapper) {
        this.funcionarioRepository = funcionarioRepository;
        this.funcionarioMapper = funcionarioMapper;
    }

    @Transactional
    public FuncionarioResponse crear(CrearFuncionarioRequest request) {
        Funcionario f = funcionarioMapper.toEntity(request);
        return funcionarioMapper.toResponse(funcionarioRepository.save(f));
    }

    @Transactional(readOnly = true)
    public List<FuncionarioResponse> listarActivos() {
        return funcionarioMapper.toResponseList(funcionarioRepository.findByActivoTrue());
    }

    @Transactional(readOnly = true)
    public FuncionarioResponse obtener(UUID id) {
        Funcionario f = funcionarioRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Funcionario no encontrado"));
        return funcionarioMapper.toResponse(f);
    }
}