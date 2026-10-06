package com.aduana.backend.domain.actor;

import jakarta.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "declarantes")
public class Declarante {

    @Id
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @Column(name = "ruc", nullable = false, unique = true, length = 13)
    private String ruc;

    @Column(name = "nombre", nullable = false, length = 200)
    private String nombre;

    @Column(name = "direccion", nullable = false, length = 300)
    private String direccion;

    @Column(name = "habilitado", nullable = false)
    private boolean habilitado;

    @Column(name = "sanciones", nullable = false)
    private int sanciones;

    protected Declarante() {
    }

    public Declarante(String ruc, String nombre, String direccion) {
        if (ruc == null || ruc.isBlank()) {
            throw new IllegalArgumentException("El RUC es obligatorio");
        }
        if (nombre == null || nombre.isBlank()) {
            throw new IllegalArgumentException("El nombre es obligatorio");
        }
        if (direccion == null || direccion.isBlank()) {
            throw new IllegalArgumentException("La dirección es obligatoria");
        }
        this.id = UUID.randomUUID();
        this.ruc = ruc;
        this.nombre = nombre;
        this.direccion = direccion;
        this.habilitado = true;
        this.sanciones = 0;
    }

    public boolean estaHabilitado() {
        return this.habilitado && this.sanciones < 3;
    }

    public void registrarSancion() {
        this.sanciones++;
        if (this.sanciones >= 3) {
            this.habilitado = false;
        }
    }

    public void rehabilitar() {
        this.habilitado = true;
        this.sanciones = 0;
    }

    public UUID getId() { return id; }
    public String getRuc() { return ruc; }
    public String getNombre() { return nombre; }
    public String getDireccion() { return direccion; }
    public boolean isHabilitado() { return habilitado; }
    public int getSanciones() { return sanciones; }
}