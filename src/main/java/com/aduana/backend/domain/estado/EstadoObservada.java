package com.aduana.backend.domain.estado;

import com.aduana.backend.domain.actor.Funcionario;
import com.aduana.backend.domain.declaracion.Declaracion;
import com.aduana.backend.domain.declaracion.Mercancia;
import com.aduana.backend.domain.declaracion.Pago;

public class EstadoObservada implements EstadoDeclaracion {

    @Override
    public void presentar(Declaracion ctx) {
        if (ctx.getMercancias().isEmpty()) {
            throw new IllegalStateException("No se puede presentar sin mercancías");
        }
        ctx.setEstado(EstadoDeclaracionEnum.PRESENTADA);
        ctx.setEstadoState(new EstadoPresentada());
        ctx.publicarEvento("REPRESENTACION", "Declaración corregida y presentada de nuevo");
    }

    @Override public void agregarMercancia(Declaracion ctx, Mercancia m) {
        ctx.agregarMercanciaInterna(m);
    }
    @Override public void asignarFuncionario(Declaracion ctx, Funcionario f) {
        throw new IllegalStateException("Debe presentarse primero");
    }
    @Override public void aprobar(Declaracion ctx) {
        throw new IllegalStateException("Debe presentarse primero");
    }
    @Override public void observar(Declaracion ctx, String o) {
        throw new IllegalStateException("Ya está observada");
    }
    @Override public void rechazar(Declaracion ctx, String m) {
        throw new IllegalStateException("Debe presentarse primero");
    }
    @Override public void liquidar(Declaracion ctx) {
        throw new IllegalStateException("Debe aprobarse primero");
    }
    @Override public void pagar(Declaracion ctx, Pago p) {
        throw new IllegalStateException("Debe liquidarse primero");
    }
    @Override public void archivar(Declaracion ctx) {
        throw new IllegalStateException("No se puede archivar todavía");
    }
}