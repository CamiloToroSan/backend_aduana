package com.aduana.backend.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CrearFuncionarioRequest(
        @NotBlank(message = "El nombre es obligatorio")
        @Size(max = 200)
        String nombre,

        @NotBlank(message = "El cargo es obligatorio")
        @Size(max = 100)
        String cargo,

        @NotBlank(message = "El turno es obligatorio")
        @Size(max = 20)
        String turno) {
}