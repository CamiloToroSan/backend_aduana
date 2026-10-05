package com.aduana.backend.domain.regimen;

import com.aduana.backend.domain.declaracion.Declaracion;
import com.aduana.backend.domain.vo.ValorAduana;
import java.math.BigDecimal;

public class CalculoMixto implements CalculoArancel {

    private final BigDecimal porcentaje;
    private final BigDecimal tarifaPorKg;

    public CalculoMixto(BigDecimal porcentaje, BigDecimal tarifaPorKg) {
        this.porcentaje = porcentaje;
        this.tarifaPorKg = tarifaPorKg;
    }

    @Override
    public ValorAduana calcular(Declaracion declaracion) {
        BigDecimal base = declaracion.getMercancias().stream()
                .map(m -> m.getValorAduana().monto())
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        BigDecimal pesoTotal = declaracion.getMercancias().stream()
                .map(m -> m.getPesoNeto().kg())
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        BigDecimal total = base.multiply(porcentaje).add(pesoTotal.multiply(tarifaPorKg));
        return new ValorAduana(total, "USD");
    }
}