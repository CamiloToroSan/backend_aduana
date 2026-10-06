package com.aduana.backend.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record CrearDeclaracionRequest(
        @NotNull(message = "El declarante es obligatorio")
        UUID declaranteId,

        @NotBlank(message = "El régimen es obligatorio")
        String regimen) {
}