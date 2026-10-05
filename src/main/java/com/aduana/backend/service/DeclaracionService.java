package com.aduana.backend.service;

import com.aduana.backend.domain.actor.Declarante;
import com.aduana.backend.domain.actor.Funcionario;
import com.aduana.backend.domain.auditoria.GestorEventos;
import com.aduana.backend.domain.declaracion.Declaracion;
import com.aduana.backend.domain.declaracion.Mercancia;
import com.aduana.backend.domain.declaracion.Pago;
import com.aduana.backend.domain.regimen.RegimenAduanero;
import com.aduana.backend.domain.vo.*;
import com.aduana.backend.dto.request.AgregarMercanciaRequest;
import com.aduana.backend.dto.request.CrearDeclaracionRequest;
import com.aduana.backend.dto.response.DeclaracionDetalleResponse;
import com.aduana.backend.mapper.DeclaracionMapper;
import com.aduana.backend.repository.DeclaracionRepository;
import com.aduana.backend.repository.DeclaranteRepository;
import com.aduana.backend.repository.FuncionarioRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.UUID;

@Service
public class DeclaracionService {

    private final DeclaracionRepository declaracionRepository;
    private final DeclaranteRepository declaranteRepository;
    private final FuncionarioRepository funcionarioRepository;
    private final DeclaracionMapper declaracionMapper;
    private final GestorEventos gestorEventos;

    public DeclaracionService(DeclaracionRepository declaracionRepository,
                              DeclaranteRepository declaranteRepository,
                              FuncionarioRepository funcionarioRepository,
                              DeclaracionMapper declaracionMapper,
                              GestorEventos gestorEventos) {
        this.declaracionRepository = declaracionRepository;
        this.declaranteRepository = declaranteRepository;
        this.funcionarioRepository = funcionarioRepository;
        this.declaracionMapper = declaracionMapper;
        this.gestorEventos = gestorEventos;
    }

    @Transactional
    public DeclaracionDetalleResponse crear(CrearDeclaracionRequest request) {
        Declarante declarante = declaranteRepository.findById(request.declaranteId())
                .orElseThrow(() -> new IllegalArgumentException("Declarante no encontrado"));
        RegimenAduanero regimen;
        try {
            regimen = RegimenAduanero.valueOf(request.regimen());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Régimen inválido: " + request.regimen());
        }

        Declaracion declaracion = new Declaracion(declarante, regimen, gestorEventos);
        declaracion.publicarEvento("CREACION", "Declaración creada");
        return declaracionMapper.toDetalle(declaracionRepository.save(declaracion));
    }

    @Transactional
    public DeclaracionDetalleResponse agregarMercancia(UUID id, AgregarMercanciaRequest request) {
        Declaracion declaracion = declaracionRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Declaración no encontrada"));

        Mercancia mercancia = new Mercancia(
                request.descripcion(),
                new Pais(request.paisOrigen()),
                new Pais(request.paisDestino()),
                new ValorAduana(request.valorAduana(), request.moneda()),
                new PesoNeto(request.pesoNeto()),
                new PesoBruto(request.pesoBruto())
        );

        declaracion.agregarMercancia(mercancia);
        return declaracionMapper.toDetalle(declaracionRepository.save(declaracion));
    }

    @Transactional
    public DeclaracionDetalleResponse presentar(UUID id) {
        Declaracion d = buscar(id);
        d.presentar();
        return declaracionMapper.toDetalle(declaracionRepository.save(d));
    }

    @Transactional
    public DeclaracionDetalleResponse asignarFuncionario(UUID id, UUID funcionarioId) {
        Declaracion d = buscar(id);
        Funcionario f = funcionarioRepository.findById(funcionarioId)
                .orElseThrow(() -> new IllegalArgumentException("Funcionario no encontrado"));
        d.asignarFuncionario(f);
        return declaracionMapper.toDetalle(declaracionRepository.save(d));
    }

    @Transactional
    public DeclaracionDetalleResponse aprobar(UUID id) {
        Declaracion d = buscar(id);
        d.aprobar();
        return declaracionMapper.toDetalle(declaracionRepository.save(d));
    }

    @Transactional
    public DeclaracionDetalleResponse observar(UUID id, String observacion) {
        Declaracion d = buscar(id);
        d.observar(observacion);
        return declaracionMapper.toDetalle(declaracionRepository.save(d));
    }

    @Transactional
    public DeclaracionDetalleResponse rechazar(UUID id, String motivo) {
        Declaracion d = buscar(id);
        d.rechazar(motivo);
        return declaracionMapper.toDetalle(declaracionRepository.save(d));
    }

    @Transactional
    public DeclaracionDetalleResponse liquidar(UUID id) {
        Declaracion d = buscar(id);
        d.liquidar();
        return declaracionMapper.toDetalle(declaracionRepository.save(d));
    }

    @Transactional
    public DeclaracionDetalleResponse pagar(UUID id, BigDecimal monto, String referencia) {
        Declaracion d = buscar(id);
        Pago pago = new Pago(new ValorAduana(monto, "USD"), referencia);
        d.pagar(pago);
        return declaracionMapper.toDetalle(declaracionRepository.save(d));
    }

    @Transactional
    public DeclaracionDetalleResponse archivar(UUID id) {
        Declaracion d = buscar(id);
        d.archivar();
        return declaracionMapper.toDetalle(declaracionRepository.save(d));
    }

    @Transactional(readOnly = true)
    public DeclaracionDetalleResponse obtener(UUID id) {
        return declaracionMapper.toDetalle(buscar(id));
    }

    private Declaracion buscar(UUID id) {
        return declaracionRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Declaración no encontrada"));
    }
}