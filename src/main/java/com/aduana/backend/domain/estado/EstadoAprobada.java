package com.aduana.backend.domain.estado;

import com.aduana.backend.domain.actor.Funcionario;
import com.aduana.backend.domain.declaracion.Declaracion;
import com.aduana.backend.domain.declaracion.Mercancia;
import com.aduana.backend.domain.declaracion.Pago;

public class EstadoAprobada implements EstadoDeclaracion {

    @Override
    public void liquidar(Declaracion ctx) {
        ctx.setEstado(EstadoDeclaracionEnum.LIQUIDADA);
        ctx.setEstadoState(new EstadoLiquidada());
        ctx.publicarEvento("LIQUIDACION", "Declaración liquidada");
    }

    @Override public void agregarMercancia(Declaracion ctx, Mercancia m) {
        throw new IllegalStateException("Aprobada, no se puede modificar");
    }
    @Override public void presentar(Declaracion ctx) {
        throw new IllegalStateException("Ya presentada");
    }
    @Override public void asignarFuncionario(Declaracion ctx, Funcionario f) {
        throw new IllegalStateException("Ya aprobada");
    }
    @Override public void aprobar(Declaracion ctx) {
        throw new IllegalStateException("Ya aprobada");
    }
    @Override public void observar(Declaracion ctx, String o) {
        throw new IllegalStateException("Ya aprobada");
    }
    @Override public void rechazar(Declaracion ctx, String m) {
        throw new IllegalStateException("Ya aprobada");
    }
    @Override public void pagar(Declaracion ctx, Pago p) {
        throw new IllegalStateException("Debe liquidarse primero");
    }
    @Override public void archivar(Declaracion ctx) {
        throw new IllegalStateException("No se puede archivar todavía");
    }
}