package com.example.app.controller;

import com.example.app.data.entity.Resource;
import com.example.app.service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(path = "/resources", produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
public class ResourceController {
    private final ResourceService resourceService;

    @Autowired
    public ResourceController(ResourceService resourceService) {
        this.resourceService = resourceService;
    }

    @PostMapping
    public ResponseEntity<Resource> createNew(@RequestBody Resource resource) {
        final var savedResource = resourceService.addResource(resource);
        final String location = "/resources/" + savedResource.getId();
        return ResponseEntity
                .created(URI.create(location))
                .body(savedResource);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Resource> getResourceById(@PathVariable Long id) {
        final Resource fetchedResource = resourceService.getResourceById(id);
        return ResponseEntity
                .status(302)
                .body(fetchedResource);
    }

    @ExceptionHandler(value = {Exception.class})
    public ResponseEntity<?> exceptionHandler(Exception exception) {
        return ResponseEntity
                .status(500)
                .body(exception);
    }
}
