package com.aduana.backend.domain.estado;

import com.aduana.backend.domain.actor.Funcionario;
import com.aduana.backend.domain.declaracion.Declaracion;
import com.aduana.backend.domain.declaracion.Mercancia;
import com.aduana.backend.domain.declaracion.Pago;

public class EstadoArchivada implements EstadoDeclaracion {

    @Override public void agregarMercancia(Declaracion ctx, Mercancia m) {
        throw new IllegalStateException("Archivada, estado terminal");
    }
    @Override public void presentar(Declaracion ctx) {
        throw new IllegalStateException("Archivada, estado terminal");
    }
    @Override public void asignarFuncionario(Declaracion ctx, Funcionario f) {
        throw new IllegalStateException("Archivada, estado terminal");
    }
    @Override public void aprobar(Declaracion ctx) {
        throw new IllegalStateException("Archivada, estado terminal");
    }
    @Override public void observar(Declaracion ctx, String o) {
        throw new IllegalStateException("Archivada, estado terminal");
    }
    @Override public void rechazar(Declaracion ctx, String m) {
        throw new IllegalStateException("Archivada, estado terminal");
    }
    @Override public void liquidar(Declaracion ctx) {
        throw new IllegalStateException("Archivada, estado terminal");
    }
    @Override public void pagar(Declaracion ctx, Pago p) {
        throw new IllegalStateException("Archivada, estado terminal");
    }
    @Override public void archivar(Declaracion ctx) {
        throw new IllegalStateException("Ya archivada");
    }
}