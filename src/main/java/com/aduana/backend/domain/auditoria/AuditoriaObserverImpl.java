package com.aduana.backend.domain.auditoria;

import com.aduana.backend.domain.declaracion.Declaracion;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class AuditoriaObserverImpl implements AuditoriaObserver {

    @Override
    public void onEvento(Declaracion declaracion, String evento, String detalle) {
        System.out.println(
            "[AUDITORIA " + LocalDateTime.now() + "] " +
            "Declaración " + declaracion.getNumero().valor() +
            " | Evento: " + evento +
            " | " + detalle
        );
    }
}