package com.aduana.backend.domain.estado;

import com.aduana.backend.domain.actor.Funcionario;
import com.aduana.backend.domain.declaracion.Declaracion;
import com.aduana.backend.domain.declaracion.Mercancia;
import com.aduana.backend.domain.declaracion.Pago;

public class EstadoBorrador implements EstadoDeclaracion {

    @Override
    public void agregarMercancia(Declaracion ctx, Mercancia mercancia) {
        ctx.agregarMercanciaInterna(mercancia);
    }

    @Override
    public void presentar(Declaracion ctx) {
        if (ctx.getMercancias().isEmpty()) {
            throw new IllegalStateException("No se puede presentar sin mercancías");
        }
        ctx.setEstado(EstadoDeclaracionEnum.PRESENTADA);
        ctx.setEstadoState(new EstadoPresentada());
        ctx.publicarEvento("PRESENTACION", "Declaración presentada");
    }

    @Override
    public void asignarFuncionario(Declaracion ctx, Funcionario f) {
        throw new IllegalStateException("No se puede asignar funcionario en borrador");
    }

    @Override
    public void aprobar(Declaracion ctx) {
        throw new IllegalStateException("No se puede aprobar en borrador");
    }

    @Override
    public void observar(Declaracion ctx, String o) {
        throw new IllegalStateException("No se puede observar en borrador");
    }

    @Override
    public void rechazar(Declaracion ctx, String m) {
        throw new IllegalStateException("No se puede rechazar en borrador");
    }

    @Override
    public void liquidar(Declaracion ctx) {
        throw new IllegalStateException("No se puede liquidar en borrador");
    }

    @Override
    public void pagar(Declaracion ctx, Pago p) {
        throw new IllegalStateException("No se puede pagar en borrador");
    }

    @Override
    public void archivar(Declaracion ctx) {
        throw new IllegalStateException("No se puede archivar en borrador");
    }
}