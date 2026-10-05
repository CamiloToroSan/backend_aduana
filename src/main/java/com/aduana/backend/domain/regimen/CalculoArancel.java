package com.aduana.backend.domain.regimen;

import com.aduana.backend.domain.declaracion.Declaracion;
import com.aduana.backend.domain.vo.ValorAduana;

public interface CalculoArancel {
    ValorAduana calcular(Declaracion declaracion);
}