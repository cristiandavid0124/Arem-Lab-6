package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.validation.annotation.Validated;

import jakarta.validation.Valid;

import com.example.demo.model.Property;
import com.example.demo.service.PropertyService;

import java.util.Optional;

@RestController
@RequestMapping("/properties")
@Validated
public class PropertyController {

    private final PropertyService propertyService;

    @Autowired
    public PropertyController(PropertyService propertyService) {
        this.propertyService = propertyService;
    }

    // Create a new property
    @PostMapping
    public ResponseEntity<Property> createProperty(@Valid @RequestBody Property property) {
        Property createdProperty = propertyService.createProperty(property);
        return new ResponseEntity<>(createdProperty, HttpStatus.CREATED);
    }

    // Get a property by ID
    @GetMapping("/{id}")
    public ResponseEntity<Property> getPropertyById(@PathVariable Long id) {
        Optional<Property> property = propertyService.getPropertyById(id);
        return property.map(ResponseEntity::ok)
                       .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Get all properties
    @GetMapping
    public Iterable<Property> getAllProperties() {
        return propertyService.getAllProperties();
    }

    // Update an existing property by ID
    @PutMapping("/{id}")
    public ResponseEntity<Property> updateProperty(@PathVariable Long id, @Valid @RequestBody Property property) {
        Property updatedProperty = propertyService.updateProperty(id, property);
        return new ResponseEntity<>(updatedProperty, HttpStatus.OK);
    }

    // Delete a property by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProperty(@PathVariable Long id) {
        propertyService.deleteProperty(id);
        return ResponseEntity.noContent().build();
    }
}
