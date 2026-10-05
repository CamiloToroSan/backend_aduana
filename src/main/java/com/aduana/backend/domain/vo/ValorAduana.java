package com.aduana.backend.domain.vo;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.math.BigDecimal;

@Embeddable
public record ValorAduana(
        @Column(name = "monto", nullable = false) BigDecimal monto,
        @Column(name = "moneda", nullable = false, length = 3) String moneda) {

    public ValorAduana {
        if (monto == null || monto.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("El monto no puede ser negativo");
        }
        if (moneda == null || moneda.length() != 3) {
            throw new IllegalArgumentException("La moneda debe ser ISO de 3 letras");
        }
    }
}