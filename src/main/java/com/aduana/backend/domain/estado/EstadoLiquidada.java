package com.aduana.backend.domain.estado;

import com.aduana.backend.domain.actor.Funcionario;
import com.aduana.backend.domain.declaracion.Declaracion;
import com.aduana.backend.domain.declaracion.Mercancia;
import com.aduana.backend.domain.declaracion.Pago;

public class EstadoLiquidada implements EstadoDeclaracion {

    @Override
    public void pagar(Declaracion ctx, Pago pago) {
        ctx.agregarPagoInterno(pago);
        ctx.setEstado(EstadoDeclaracionEnum.PAGADA);
        ctx.setEstadoState(new EstadoPagada());
        ctx.publicarEvento("PAGO", "Pago registrado: " + pago.getReferencia());
    }

    @Override public void agregarMercancia(Declaracion ctx, Mercancia m) {
        throw new IllegalStateException("Liquidada, no se puede modificar");
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
    @Override public void liquidar(Declaracion ctx) {
        throw new IllegalStateException("Ya liquidada");
    }
    @Override public void archivar(Declaracion ctx) {
        throw new IllegalStateException("Debe pagarse primero");
    }
}