package com.aduana.backend.domain.declaracion;

import com.aduana.backend.domain.vo.Pais;
import com.aduana.backend.domain.vo.PesoBruto;
import com.aduana.backend.domain.vo.PesoNeto;
import com.aduana.backend.domain.vo.ValorAduana;
import jakarta.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "mercancias")
public class Mercancia {

    @Id
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "declaracion_id", nullable = false)
    private Declaracion declaracion;

    @Column(name = "descripcion", nullable = false, length = 500)
    private String descripcion;

    @Embedded
    @AttributeOverride(name = "codigo", column = @Column(name = "origen", nullable = false))
    private Pais origen;

    @Embedded
    @AttributeOverride(name = "codigo", column = @Column(name = "destino", nullable = false))
    private Pais destino;

    @Embedded
    @AttributeOverrides({
        @AttributeOverride(name = "monto", column = @Column(name = "valor_aduana_monto", nullable = false)),
        @AttributeOverride(name = "moneda", column = @Column(name = "valor_aduana_moneda", nullable = false, length = 3))
    })
    private ValorAduana valorAduana;

    @Embedded
    @AttributeOverride(name = "kg", column = @Column(name = "peso_neto", nullable = false))
    private PesoNeto pesoNeto;

    @Embedded
    @AttributeOverride(name = "kg", column = @Column(name = "peso_bruto", nullable = false))
    private PesoBruto pesoBruto;

    protected Mercancia() {
    }

    public Mercancia(String descripcion, Pais origen, Pais destino,
                     ValorAduana valorAduana, PesoNeto pesoNeto, PesoBruto pesoBruto) {
        if (descripcion == null || descripcion.isBlank()) {
            throw new IllegalArgumentException("La descripción es obligatoria");
        }
        if (origen == null || destino == null) {
            throw new IllegalArgumentException("Origen y destino son obligatorios");
        }
        if (valorAduana == null || pesoNeto == null || pesoBruto == null) {
            throw new IllegalArgumentException("Valor aduana y pesos son obligatorios");
        }
        this.id = UUID.randomUUID();
        this.descripcion = descripcion;
        this.origen = origen;
        this.destino = destino;
        this.valorAduana = valorAduana;
        this.pesoNeto = pesoNeto;
        this.pesoBruto = pesoBruto;
    }

    void setDeclaracion(Declaracion declaracion) {
        this.declaracion = declaracion;
    }

    public UUID getId() { return id; }
    public String getDescripcion() { return descripcion; }
    public Pais getOrigen() { return origen; }
    public Pais getDestino() { return destino; }
    public ValorAduana getValorAduana() { return valorAduana; }
    public PesoNeto getPesoNeto() { return pesoNeto; }
    public PesoBruto getPesoBruto() { return pesoBruto; }
}