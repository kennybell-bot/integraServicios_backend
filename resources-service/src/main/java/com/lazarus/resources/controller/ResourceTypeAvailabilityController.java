package com.lazarus.resources.controller;

import com.lazarus.resources.model.ResourceTypeAvailability;
import com.lazarus.resources.service.ResourceTypeAvailabilityService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/availability")
public class ResourceTypeAvailabilityController {

    private final ResourceTypeAvailabilityService service;

    public ResourceTypeAvailabilityController(ResourceTypeAvailabilityService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<ResourceTypeAvailability>> getAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/type/{typeId}")
    public ResponseEntity<List<ResourceTypeAvailability>> getByType(@PathVariable UUID typeId) {
        List<ResourceTypeAvailability> result = service.findByResourceTypeId(typeId);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResourceTypeAvailability> getById(@PathVariable UUID id) {
        ResourceTypeAvailability availability = service.findById(id);
        return ResponseEntity.ok(availability);
    }

    @SuppressWarnings("null")
    @PostMapping
    public ResponseEntity<ResourceTypeAvailability> create(@RequestBody ResourceTypeAvailability a) {
        ResourceTypeAvailability created = service.create(a);
        return ResponseEntity
                .created(URI.create("/availability/" + created.getId()))
                .body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResourceTypeAvailability> update(
            @PathVariable UUID id,
            @RequestBody ResourceTypeAvailability a
    ) {
        ResourceTypeAvailability updated = service.update(id, a);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        service.delete(id);
        return ResponseEntity.noContent().build(); // 204 limpio y profesional
    }
}
