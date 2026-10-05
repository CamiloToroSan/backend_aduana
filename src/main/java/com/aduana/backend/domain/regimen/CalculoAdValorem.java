package com.aduana.backend.domain.regimen;

import com.aduana.backend.domain.declaracion.Declaracion;
import com.aduana.backend.domain.vo.ValorAduana;
import java.math.BigDecimal;

public class CalculoAdValorem implements CalculoArancel {

    private final BigDecimal porcentaje;

    public CalculoAdValorem(BigDecimal porcentaje) {
        this.porcentaje = porcentaje;
    }

    @Override
    public ValorAduana calcular(Declaracion declaracion) {
        BigDecimal base = declaracion.getMercancias().stream()
                .map(m -> m.getValorAduana().monto())
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        return new ValorAduana(base.multiply(porcentaje), "USD");
    }
}