package com.aduana.backend.domain.vo;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.util.regex.Pattern;

@Embeddable
public record Pais(
        @Column(name = "codigo", nullable = false, length = 2) String codigo) {

    private static final Pattern PATTERN = Pattern.compile("^[A-Z]{2}$");

    public Pais {
        if (codigo == null || !PATTERN.matcher(codigo).matches()) {
            throw new IllegalArgumentException(
                "El código de país debe ser ISO de 2 letras mayúsculas");
        }
    }
}