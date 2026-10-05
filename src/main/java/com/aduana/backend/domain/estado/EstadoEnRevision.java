package com.aduana.backend.domain.estado;

import com.aduana.backend.domain.actor.Funcionario;
import com.aduana.backend.domain.declaracion.Declaracion;
import com.aduana.backend.domain.declaracion.Mercancia;
import com.aduana.backend.domain.declaracion.Pago;

public class EstadoEnRevision implements EstadoDeclaracion {

    @Override
    public void aprobar(Declaracion ctx) {
        ctx.setEstado(EstadoDeclaracionEnum.APROBADA);
        ctx.setEstadoState(new EstadoAprobada());
        ctx.publicarEvento("APROBACION", "Declaración aprobada");
    }

    @Override
    public void observar(Declaracion ctx, String observacion) {
        ctx.setEstado(EstadoDeclaracionEnum.OBSERVADA);
        ctx.setEstadoState(new EstadoObservada());
        ctx.publicarEvento("OBSERVACION", observacion);
    }

    @Override
    public void rechazar(Declaracion ctx, String motivo) {
        ctx.setEstado(EstadoDeclaracionEnum.RECHAZADA);
        ctx.setEstadoState(new EstadoRechazada());
        ctx.publicarEvento("RECHAZO", motivo);
    }

    @Override public void agregarMercancia(Declaracion ctx, Mercancia m) {
        throw new IllegalStateException("En revisión, no se puede modificar");
    }
    @Override public void presentar(Declaracion ctx) {
        throw new IllegalStateException("Ya está presentada");
    }
    @Override public void asignarFuncionario(Declaracion ctx, Funcionario f) {
        throw new IllegalStateException("Ya tiene funcionario asignado");
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