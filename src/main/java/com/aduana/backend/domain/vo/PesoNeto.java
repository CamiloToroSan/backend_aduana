package com.aduana.backend.domain.vo;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.math.BigDecimal;

@Embeddable
public record PesoNeto(
        @Column(name = "kg", nullable = false) BigDecimal kg) {

    public PesoNeto {
        if (kg == null || kg.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("El peso neto debe ser mayor a cero");
        }
    }
}