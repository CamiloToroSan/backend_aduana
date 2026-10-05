package com.aduana.backend.service;

import com.aduana.backend.domain.actor.Declarante;
import com.aduana.backend.dto.request.CrearDeclaranteRequest;
import com.aduana.backend.dto.response.DeclaranteResponse;
import com.aduana.backend.mapper.DeclaranteMapper;
import com.aduana.backend.repository.DeclaranteRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class DeclaranteService {

    private final DeclaranteRepository declaranteRepository;
    private final DeclaranteMapper declaranteMapper;

    public DeclaranteService(DeclaranteRepository declaranteRepository,
                             DeclaranteMapper declaranteMapper) {
        this.declaranteRepository = declaranteRepository;
        this.declaranteMapper = declaranteMapper;
    }

    @Transactional
    public DeclaranteResponse crear(CrearDeclaranteRequest request) {
        if (declaranteRepository.existsByRuc(request.ruc())) {
            throw new IllegalArgumentException("Ya existe un declarante con ese RUC");
        }
        Declarante declarante = declaranteMapper.toEntity(request);
        Declarante guardado = declaranteRepository.save(declarante);
        return declaranteMapper.toResponse(guardado);
    }

    @Transactional(readOnly = true)
    public List<DeclaranteResponse> listar() {
        return declaranteMapper.toResponseList(declaranteRepository.findAll());
    }

    @Transactional(readOnly = true)
    public DeclaranteResponse obtener(UUID id) {
        Declarante d = declaranteRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Declarante no encontrado"));
        return declaranteMapper.toResponse(d);
    }

    @Transactional
    public DeclaranteResponse sancionar(UUID id) {
        Declarante d = declaranteRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Declarante no encontrado"));
        d.registrarSancion();
        return declaranteMapper.toResponse(declaranteRepository.save(d));
    }

    @Transactional
    public DeclaranteResponse rehabilitar(UUID id) {
        Declarante d = declaranteRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Declarante no encontrado"));
        d.rehabilitar();
        return declaranteMapper.toResponse(declaranteRepository.save(d));
    }
}