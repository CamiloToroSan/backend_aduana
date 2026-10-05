package com.aduana.backend.dto.response;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public record DeclaracionDetalleResponse(
        UUID id,
        String numero,
        String declaranteNombre,
        String declaranteRuc,
        String funcionarioNombre,
        String regimen,
        String estado,
        LocalDateTime fechaCreacion,
        BigDecimal totalTributos,
        String monedaTributos,
        List<MercanciaItemResponse> mercancias) {
}