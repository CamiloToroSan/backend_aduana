package com.aduana.backend.controller;

import com.aduana.backend.dto.request.AgregarMercanciaRequest;
import com.aduana.backend.dto.request.CrearDeclaracionRequest;
import com.aduana.backend.dto.response.DeclaracionDetalleResponse;
import com.aduana.backend.service.DeclaracionService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.math.BigDecimal;
import java.net.URI;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/declaraciones")
public class DeclaracionController {

    private final DeclaracionService service;

    public DeclaracionController(DeclaracionService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<DeclaracionDetalleResponse> crear(
            @Valid @RequestBody CrearDeclaracionRequest request,
            UriComponentsBuilder uriBuilder) {
        DeclaracionDetalleResponse response = service.crear(request);
        URI uri = uriBuilder.path("/api/declaraciones/{id}").buildAndExpand(response.id()).toUri();
        return ResponseEntity.created(uri).body(response);
    }

    @PostMapping("/{id}/mercancias")
    public ResponseEntity<DeclaracionDetalleResponse> agregarMercancia(
            @PathVariable UUID id,
            @Valid @RequestBody AgregarMercanciaRequest request) {
        return ResponseEntity.ok(service.agregarMercancia(id, request));
    }

    @PostMapping("/{id}/presentar")
    public ResponseEntity<DeclaracionDetalleResponse> presentar(@PathVariable UUID id) {
        return ResponseEntity.ok(service.presentar(id));
    }

    @PostMapping("/{id}/asignar-funcionario/{funcionarioId}")
    public ResponseEntity<DeclaracionDetalleResponse> asignarFuncionario(
            @PathVariable UUID id,
            @PathVariable UUID funcionarioId) {
        return ResponseEntity.ok(service.asignarFuncionario(id, funcionarioId));
    }

    @PostMapping("/{id}/aprobar")
    public ResponseEntity<DeclaracionDetalleResponse> aprobar(@PathVariable UUID id) {
        return ResponseEntity.ok(service.aprobar(id));
    }

    @PostMapping("/{id}/observar")
    public ResponseEntity<DeclaracionDetalleResponse> observar(
            @PathVariable UUID id,
            @RequestBody Map<String, String> body) {
        return ResponseEntity.ok(service.observar(id, body.getOrDefault("observacion", "")));
    }

    @PostMapping("/{id}/rechazar")
    public ResponseEntity<DeclaracionDetalleResponse> rechazar(
            @PathVariable UUID id,
            @RequestBody Map<String, String> body) {
        return ResponseEntity.ok(service.rechazar(id, body.getOrDefault("motivo", "")));
    }

    @PostMapping("/{id}/liquidar")
    public ResponseEntity<DeclaracionDetalleResponse> liquidar(@PathVariable UUID id) {
        return ResponseEntity.ok(service.liquidar(id));
    }

    @PostMapping("/{id}/pagar")
    public ResponseEntity<DeclaracionDetalleResponse> pagar(
            @PathVariable UUID id,
            @RequestBody Map<String, Object> body) {
        BigDecimal monto = new BigDecimal(body.get("monto").toString());
        String referencia = body.get("referencia").toString();
        return ResponseEntity.ok(service.pagar(id, monto, referencia));
    }

    @PostMapping("/{id}/archivar")
    public ResponseEntity<DeclaracionDetalleResponse> archivar(@PathVariable UUID id) {
        return ResponseEntity.ok(service.archivar(id));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DeclaracionDetalleResponse> obtener(@PathVariable UUID id) {
        return ResponseEntity.ok(service.obtener(id));
    }
}