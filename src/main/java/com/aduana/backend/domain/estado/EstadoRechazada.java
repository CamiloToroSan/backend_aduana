package com.aduana.backend.domain.estado;

import com.aduana.backend.domain.actor.Funcionario;
import com.aduana.backend.domain.declaracion.Declaracion;
import com.aduana.backend.domain.declaracion.Mercancia;
import com.aduana.backend.domain.declaracion.Pago;

public class EstadoRechazada implements EstadoDeclaracion {

    @Override public void agregarMercancia(Declaracion ctx, Mercancia m) {
        throw new IllegalStateException("Rechazada, estado terminal");
    }
    @Override public void presentar(Declaracion ctx) {
        throw new IllegalStateException("Rechazada, estado terminal");
    }
    @Override public void asignarFuncionario(Declaracion ctx, Funcionario f) {
        throw new IllegalStateException("Rechazada, estado terminal");
    }
    @Override public void aprobar(Declaracion ctx) {
        throw new IllegalStateException("Rechazada, estado terminal");
    }
    @Override public void observar(Declaracion ctx, String o) {
        throw new IllegalStateException("Rechazada, estado terminal");
    }
    @Override public void rechazar(Declaracion ctx, String m) {
        throw new IllegalStateException("Ya rechazada");
    }
    @Override public void liquidar(Declaracion ctx) {
        throw new IllegalStateException("Rechazada, estado terminal");
    }
    @Override public void pagar(Declaracion ctx, Pago p) {
        throw new IllegalStateException("Rechazada, estado terminal");
    }
    @Override public void archivar(Declaracion ctx) {
        ctx.setEstado(EstadoDeclaracionEnum.ARCHIVADA);
        ctx.setEstadoState(new EstadoArchivada());
    }
}