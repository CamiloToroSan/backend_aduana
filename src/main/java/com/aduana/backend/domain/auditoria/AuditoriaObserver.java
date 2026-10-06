package com.aduana.backend.domain.auditoria;

import com.aduana.backend.domain.declaracion.Declaracion;

public interface AuditoriaObserver {
    void onEvento(Declaracion declaracion, String evento, String detalle);
}