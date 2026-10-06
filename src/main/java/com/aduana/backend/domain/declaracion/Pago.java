package com.aduana.backend.domain.declaracion;

import com.aduana.backend.domain.vo.ValorAduana;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "pagos")
public class Pago {

    @Id
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "declaracion_id", nullable = false)
    private Declaracion declaracion;

    @Embedded
    @AttributeOverrides({
        @AttributeOverride(name = "monto", column = @Column(name = "monto", nullable = false)),
        @AttributeOverride(name = "moneda", column = @Column(name = "moneda", nullable = false, length = 3))
    })
    private ValorAduana monto;

    @Column(name = "referencia", nullable = false, length = 100)
    private String referencia;

    @Column(name = "fecha_pago", nullable = false)
    private LocalDateTime fechaPago;

    protected Pago() {
    }

    public Pago(ValorAduana monto, String referencia) {
        if (monto == null) {
            throw new IllegalArgumentException("El monto es obligatorio");
        }
        if (referencia == null || referencia.isBlank()) {
            throw new IllegalArgumentException("La referencia es obligatoria");
        }
        this.id = UUID.randomUUID();
        this.monto = monto;
        this.referencia = referencia;
        this.fechaPago = LocalDateTime.now();
    }

    void setDeclaracion(Declaracion declaracion) {
        this.declaracion = declaracion;
    }

    public UUID getId() { return id; }
    public ValorAduana getMonto() { return monto; }
    public String getReferencia() { return referencia; }
    public LocalDateTime getFechaPago() { return fechaPago; }
}