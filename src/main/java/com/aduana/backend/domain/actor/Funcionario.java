package com.aduana.backend.domain.actor;

import jakarta.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "funcionarios")
public class Funcionario {

    @Id
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @Column(name = "nombre", nullable = false, length = 200)
    private String nombre;

    @Column(name = "cargo", nullable = false, length = 100)
    private String cargo;

    @Column(name = "turno", nullable = false, length = 20)
    private String turno;

    @Column(name = "activo", nullable = false)
    private boolean activo;

    protected Funcionario() {
    }

    public Funcionario(String nombre, String cargo, String turno) {
        if (nombre == null || nombre.isBlank()) {
            throw new IllegalArgumentException("El nombre es obligatorio");
        }
        if (cargo == null || cargo.isBlank()) {
            throw new IllegalArgumentException("El cargo es obligatorio");
        }
        this.id = UUID.randomUUID();
        this.nombre = nombre;
        this.cargo = cargo;
        this.turno = turno;
        this.activo = true;
    }

    public boolean puedeRevisar() {
        return this.activo;
    }

    public void desactivar() {
        this.activo = false;
    }

    public void activar() {
        this.activo = true;
    }

    public UUID getId() { return id; }
    public String getNombre() { return nombre; }
    public String getCargo() { return cargo; }
    public String getTurno() { return turno; }
    public boolean isActivo() { return activo; }
}