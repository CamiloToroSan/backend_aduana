package com.aduana.backend.domain.vo;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.math.BigDecimal;

@Embeddable
public record PesoBruto(
        @Column(name = "kg", nullable = false) BigDecimal kg) {

    public PesoBruto {
        if (kg == null || kg.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("El peso bruto debe ser mayor a cero");
        }
    }
}