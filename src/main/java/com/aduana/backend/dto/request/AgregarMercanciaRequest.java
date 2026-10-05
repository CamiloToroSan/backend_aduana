package com.aduana.backend.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record AgregarMercanciaRequest(
        @NotBlank(message = "La descripción es obligatoria")
        String descripcion,

        @NotBlank(message = "El país de origen es obligatorio")
        String paisOrigen,

        @NotBlank(message = "El país de destino es obligatorio")
        String paisDestino,

        @NotNull(message = "El valor aduana es obligatorio")
        @Positive(message = "El valor debe ser positivo")
        BigDecimal valorAduana,

        @NotBlank(message = "La moneda es obligatoria")
        String moneda,

        @NotNull(message = "El peso neto es obligatorio")
        @Positive(message = "El peso neto debe ser positivo")
        BigDecimal pesoNeto,

        @NotNull(message = "El peso bruto es obligatorio")
        @Positive(message = "El peso bruto debe ser positivo")
        BigDecimal pesoBruto) {
}