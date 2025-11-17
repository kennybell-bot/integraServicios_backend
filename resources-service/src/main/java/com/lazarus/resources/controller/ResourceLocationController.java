package com.lazarus.resources.controller;

import com.lazarus.resources.model.ResourceLocation;
import com.lazarus.resources.service.ResourceLocationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/locations")
public class ResourceLocationController {

    private final ResourceLocationService service;

    public ResourceLocationController(ResourceLocationService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<ResourceLocation>> getAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResourceLocation> getById(@PathVariable UUID id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PostMapping
    public ResponseEntity<ResourceLocation> create(@RequestBody ResourceLocation location) {
        ResourceLocation created = service.create(location);
        return ResponseEntity.status(201).body(created); // 201 CREATED
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResourceLocation> update(
            @PathVariable UUID id,
            @RequestBody ResourceLocation location
    ) {
        return ResponseEntity.ok(service.update(id, location)); // 200 OK
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        service.delete(id);
        return ResponseEntity.noContent().build(); // 204 NO CONTENT
    }
}
