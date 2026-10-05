package com.aduana.backend.domain.declaracion;

import java.util.regex.Pattern;

public record NumeroDeclaracion(String valor) {

    private static final Pattern PATTERN = Pattern.compile("^DA-\\d{4}-\\d{8}$");

    public NumeroDeclaracion {
        if (valor == null || !PATTERN.matcher(valor).matches()) {
            throw new IllegalArgumentException(
                "Formato inválido. Esperado: DA-YYYY-NNNNNNNN");
        }
    }
}