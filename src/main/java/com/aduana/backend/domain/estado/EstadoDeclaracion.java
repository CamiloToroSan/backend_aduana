package com.aduana.backend.domain.estado;

import com.aduana.backend.domain.actor.Funcionario;
import com.aduana.backend.domain.declaracion.Declaracion;
import com.aduana.backend.domain.declaracion.Mercancia;
import com.aduana.backend.domain.declaracion.Pago;

public interface EstadoDeclaracion {

    void agregarMercancia(Declaracion ctx, Mercancia mercancia);
    void presentar(Declaracion ctx);
    void asignarFuncionario(Declaracion ctx, Funcionario funcionario);
    void aprobar(Declaracion ctx);
    void observar(Declaracion ctx, String observacion);
    void rechazar(Declaracion ctx, String motivo);
    void liquidar(Declaracion ctx);
    void pagar(Declaracion ctx, Pago pago);
    void archivar(Declaracion ctx);
}