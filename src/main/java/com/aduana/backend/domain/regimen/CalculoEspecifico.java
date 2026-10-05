package com.aduana.backend.domain.regimen;

import com.aduana.backend.domain.declaracion.Declaracion;
import com.aduana.backend.domain.vo.ValorAduana;
import java.math.BigDecimal;

public class CalculoEspecifico implements CalculoArancel {

    private final BigDecimal tarifaPorKg;

    public CalculoEspecifico(BigDecimal tarifaPorKg) {
        this.tarifaPorKg = tarifaPorKg;
    }

    @Override
    public ValorAduana calcular(Declaracion declaracion) {
        BigDecimal pesoTotal = declaracion.getMercancias().stream()
                .map(m -> m.getPesoNeto().kg())
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        return new ValorAduana(pesoTotal.multiply(tarifaPorKg), "USD");
    }
}