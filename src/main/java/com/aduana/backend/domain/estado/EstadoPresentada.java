package com.aduana.backend.domain.estado;

import com.aduana.backend.domain.actor.Funcionario;
import com.aduana.backend.domain.declaracion.Declaracion;
import com.aduana.backend.domain.declaracion.Mercancia;
import com.aduana.backend.domain.declaracion.Pago;

public class EstadoPresentada implements EstadoDeclaracion {

    @Override
    public void asignarFuncionario(Declaracion ctx, Funcionario f) {
        if (!f.puedeRevisar()) {
            throw new IllegalStateException("Funcionario no activo");
        }
        ctx.setFuncionarioAsignado(f);
        ctx.setEstado(EstadoDeclaracionEnum.EN_REVISION);
        ctx.setEstadoState(new EstadoEnRevision());
        ctx.publicarEvento("ASIGNACION", "Funcionario asignado: " + f.getNombre());
    }

    @Override public void agregarMercancia(Declaracion ctx, Mercancia m) {
        throw new IllegalStateException("Declaración ya presentada");
    }
    @Override public void presentar(Declaracion ctx) {
        throw new IllegalStateException("Ya está presentada");
    }
    @Override public void aprobar(Declaracion ctx) {
        throw new IllegalStateException("Debe asignarse funcionario primero");
    }
    @Override public void observar(Declaracion ctx, String o) {
        throw new IllegalStateException("Debe asignarse funcionario primero");
    }
    @Override public void rechazar(Declaracion ctx, String m) {
        throw new IllegalStateException("Debe asignarse funcionario primero");
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