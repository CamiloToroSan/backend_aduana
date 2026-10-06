package com.aduana.backend.domain.regimen;

import com.aduana.backend.domain.declaracion.Declaracion;
import com.aduana.backend.domain.vo.ValorAduana;
import java.math.BigDecimal;

public enum RegimenAduanero {

    IMPORTACION_DEFINITIVA(new CalculoAdValorem(new BigDecimal("0.15"))),
    IMPORTACION_TEMPORAL(new CalculoAdValorem(new BigDecimal("0.05"))),
    EXPORTACION(new CalculoAdValorem(new BigDecimal("0.00"))),
    TRANSITO(new CalculoAdValorem(new BigDecimal("0.02"))),
    ZONA_FRANCA(new CalculoAdValorem(new BigDecimal("0.00")));

    private final CalculoArancel calculo;

    RegimenAduanero(CalculoArancel calculo) {
        this.calculo = calculo;
    }

    public ValorAduana calcularTributos(Declaracion declaracion) {
        return calculo.calcular(declaracion);
    }
}
}
