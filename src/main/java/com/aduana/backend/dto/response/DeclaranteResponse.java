package com.aduana.backend.dto.response;

import java.util.UUID;

public record DeclaranteResponse(
        UUID id,
        String ruc,
        String nombre,
        String direccion,
        boolean habilitado,
        int sanciones) {
}