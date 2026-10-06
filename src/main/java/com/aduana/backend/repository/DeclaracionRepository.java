package com.aduana.backend.repository;

import com.aduana.backend.domain.declaracion.Declaracion;
import com.aduana.backend.domain.estado.EstadoDeclaracionEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface DeclaracionRepository extends JpaRepository<Declaracion, UUID> {
    List<Declaracion> findByDeclaranteId(UUID declaranteId);
    List<Declaracion> findByFuncionarioAsignadoId(UUID funcionarioId);
    List<Declaracion> findByEstado(EstadoDeclaracionEnum estado);
}