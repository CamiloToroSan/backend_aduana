package com.aduana.backend.controller;

import com.aduana.backend.dto.request.CrearFuncionarioRequest;
import com.aduana.backend.dto.response.FuncionarioResponse;
import com.aduana.backend.service.FuncionarioService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/funcionarios")
public class FuncionarioController {

    private final FuncionarioService service;

    public FuncionarioController(FuncionarioService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<FuncionarioResponse> crear(@Valid @RequestBody CrearFuncionarioRequest request,
                                                     UriComponentsBuilder uriBuilder) {
        FuncionarioResponse response = service.crear(request);
        URI uri = uriBuilder.path("/api/funcionarios/{id}").buildAndExpand(response.id()).toUri();
        return ResponseEntity.created(uri).body(response);
    }

    @GetMapping
    public ResponseEntity<List<FuncionarioResponse>> listarActivos() {
        return ResponseEntity.ok(service.listarActivos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<FuncionarioResponse> obtener(@PathVariable UUID id) {
        return ResponseEntity.ok(service.obtener(id));
    }
}