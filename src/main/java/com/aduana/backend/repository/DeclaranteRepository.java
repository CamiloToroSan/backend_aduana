package com.aduana.backend.repository;

import com.aduana.backend.domain.actor.Declarante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface DeclaranteRepository extends JpaRepository<Declarante, UUID> {
    Optional<Declarante> findByRuc(String ruc);
    boolean existsByRuc(String ruc);
}