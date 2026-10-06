package com.aduana.backend.dto.response;

import java.util.UUID;

public record FuncionarioResponse(
        UUID id,
        String nombre,
        String cargo,
        String turno,
        boolean activo) {
}