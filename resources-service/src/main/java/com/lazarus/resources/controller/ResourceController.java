package com.lazarus.resources.controller;

import com.lazarus.resources.model.Resource;
import com.lazarus.resources.service.ResourceService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/resources")
public class ResourceController {

    private final ResourceService service;

    public ResourceController(ResourceService service) {
        this.service = service;
    }

    // --------------------------------------------------------
    // GET /resources
    // --------------------------------------------------------
    @GetMapping
    public ResponseEntity<List<Resource>> getAll() {
        return ResponseEntity.ok(service.findAll());
    }

    // --------------------------------------------------------
    // GET /resources/{id}
    // --------------------------------------------------------
    @GetMapping("/{id}")
    public ResponseEntity<Resource> getById(@PathVariable UUID id) {
        Resource resource = service.findById(id);

        if (resource == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(resource);
    }

    // --------------------------------------------------------
    // POST /resources
    // --------------------------------------------------------
    @SuppressWarnings("null")
    @PostMapping
    public ResponseEntity<Resource> create(@RequestBody Resource resource) {
        Resource created = service.create(resource);

        return ResponseEntity
                .created(URI.create("/resources/" + created.getId()))
                .body(created);
    }

    // --------------------------------------------------------
    // PUT /resources/{id}
    // --------------------------------------------------------
    @PutMapping("/{id}")
    public ResponseEntity<Resource> update(
            @PathVariable UUID id,
            @RequestBody Resource resource
    ) {
        Resource updated = service.update(id, resource);

        if (updated == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(updated);
    }

    // --------------------------------------------------------
    // DELETE /resources/{id}
    // --------------------------------------------------------
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        try {
            service.delete(id);
            return ResponseEntity.noContent().build(); // 204
        } catch (RuntimeException ex) {
            return ResponseEntity.notFound().build();
        }
    }
}

