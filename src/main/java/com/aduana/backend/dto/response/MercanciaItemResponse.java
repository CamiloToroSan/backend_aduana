package com.aduana.backend.dto.response;

import java.math.BigDecimal;
import java.util.UUID;

public record MercanciaItemResponse(
        UUID id,
        String descripcion,
        String paisOrigen,
        String paisDestino,
        BigDecimal valorAduana,
        String moneda,
        BigDecimal pesoNeto,
        BigDecimal pesoBruto) {
}