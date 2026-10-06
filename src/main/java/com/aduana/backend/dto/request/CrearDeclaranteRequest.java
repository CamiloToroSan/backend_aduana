package com.aduana.backend.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CrearDeclaranteRequest(
        @NotBlank(message = "El RUC es obligatorio")
        @Size(min = 13, max = 13, message = "El RUC debe tener 13 caracteres")
        String ruc,

        @NotBlank(message = "El nombre es obligatorio")
        @Size(max = 200)
        String nombre,

        @NotBlank(message = "La dirección es obligatoria")
        @Size(max = 300)
        String direccion) {
}