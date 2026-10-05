package com.aduana.backend.domain.declaracion;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "documentos")
public class Documento {

    @Id
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "declaracion_id", nullable = false)
    private Declaracion declaracion;

    @Column(name = "tipo", nullable = false, length = 50)
    private String tipo;

    @Column(name = "nombre_archivo", nullable = false, length = 200)
    private String nombreArchivo;

    @Column(name = "fecha_carga", nullable = false)
    private LocalDateTime fechaCarga;

    protected Documento() {
    }

    public Documento(String tipo, String nombreArchivo) {
        if (tipo == null || tipo.isBlank()) {
            throw new IllegalArgumentException("El tipo de documento es obligatorio");
        }
        if (nombreArchivo == null || nombreArchivo.isBlank()) {
            throw new IllegalArgumentException("El nombre del archivo es obligatorio");
        }
        this.id = UUID.randomUUID();
        this.tipo = tipo;
        this.nombreArchivo = nombreArchivo;
        this.fechaCarga = LocalDateTime.now();
    }

    void setDeclaracion(Declaracion declaracion) {
        this.declaracion = declaracion;
    }

    public UUID getId() { return id; }
    public String getTipo() { return tipo; }
    public String getNombreArchivo() { return nombreArchivo; }
    public LocalDateTime getFechaCarga() { return fechaCarga; }
}