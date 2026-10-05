package com.aduana.backend.controller;

import com.aduana.backend.dto.request.CrearDeclaranteRequest;
import com.aduana.backend.dto.response.DeclaranteResponse;
import com.aduana.backend.service.DeclaranteService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/declarantes")
public class DeclaranteController {

    private final DeclaranteService service;

    public DeclaranteController(DeclaranteService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<DeclaranteResponse> crear(@Valid @RequestBody CrearDeclaranteRequest request,
                                                    UriComponentsBuilder uriBuilder) {
        DeclaranteResponse response = service.crear(request);
        URI uri = uriBuilder.path("/api/declarantes/{id}").buildAndExpand(response.id()).toUri();
        return ResponseEntity.created(uri).body(response);
    }

    @GetMapping
    public ResponseEntity<List<DeclaranteResponse>> listar() {
        return ResponseEntity.ok(service.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DeclaranteResponse> obtener(@PathVariable UUID id) {
        return ResponseEntity.ok(service.obtener(id));
    }

    @PostMapping("/{id}/sancionar")
    public ResponseEntity<DeclaranteResponse> sancionar(@PathVariable UUID id) {
        return ResponseEntity.ok(service.sancionar(id));
    }

    @PostMapping("/{id}/rehabilitar")
    public ResponseEntity<DeclaranteResponse> rehabilitar(@PathVariable UUID id) {
        return ResponseEntity.ok(service.rehabilitar(id));
    }
}