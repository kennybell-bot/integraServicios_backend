package com.lazarus.resources.controller;

import com.lazarus.resources.model.ResourceType;
import com.lazarus.resources.service.ResourceTypeService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/resource-types")
public class ResourceTypeController {

    private final ResourceTypeService service;

    public ResourceTypeController(ResourceTypeService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<ResourceType>> getAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResourceType> getById(@PathVariable UUID id) {
        ResourceType type = service.findById(id);
        return ResponseEntity.ok(type);
    }

    @SuppressWarnings("null")
    @PostMapping
    public ResponseEntity<ResourceType> create(@RequestBody ResourceType type) {
        ResourceType created = service.create(type);
        return ResponseEntity
                .created(URI.create("/resource-types/" + created.getId()))
                .body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResourceType> update(
            @PathVariable UUID id,
            @RequestBody ResourceType type
    ) {
        ResourceType updated = service.update(id, type);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        service.delete(id);
        return ResponseEntity.noContent().build(); // 204 profesional y limpio
    }
}
