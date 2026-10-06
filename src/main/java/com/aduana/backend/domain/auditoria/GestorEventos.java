package com.aduana.backend.domain.auditoria;

import com.aduana.backend.domain.declaracion.Declaracion;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class GestorEventos {

    private final List<AuditoriaObserver> observadores = new ArrayList<>();

    public void suscribir(AuditoriaObserver observador) {
        if (observador != null && !observadores.contains(observador)) {
            observadores.add(observador);
        }
    }

    public void desuscribir(AuditoriaObserver observador) {
        observadores.remove(observador);
    }

    public void publicar(Declaracion declaracion, String evento, String detalle) {
        for (AuditoriaObserver observador : observadores) {
            observador.onEvento(declaracion, evento, detalle);
        }
    }
}