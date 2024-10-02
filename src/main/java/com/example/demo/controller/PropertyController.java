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

    /**
     * Constructor para inyectar la dependencia del servicio de propiedades.
     * 
     * @param propertyService El servicio de propiedades que maneja la lógica de negocio.
     */
    @Autowired
    public PropertyController(PropertyService propertyService) {
        this.propertyService = propertyService;
    }

    /**
     * Crea una nueva propiedad en el sistema.
     * 
     * @param property Objeto Property que contiene los detalles de la nueva propiedad.
     * @return ResponseEntity con la propiedad creada y el estado HTTP 201 (CREATED).
     */
    @PostMapping
    public ResponseEntity<Property> createProperty(@Valid @RequestBody Property property) {
        Property createdProperty = propertyService.createProperty(property);
        return new ResponseEntity<>(createdProperty, HttpStatus.CREATED);
    }

    /**
     * Obtiene una propiedad por su ID.
     * 
     * @param id El ID de la propiedad a buscar.
     * @return ResponseEntity con la propiedad encontrada o una respuesta HTTP 404 (NOT FOUND) si no existe.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Property> getPropertyById(@PathVariable Long id) {
        Optional<Property> property = propertyService.getPropertyById(id);
        return property.map(ResponseEntity::ok)
                       .orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * Obtiene todas las propiedades disponibles.
     * 
     * @return Un iterable con todas las propiedades registradas en el sistema.
     */
    @GetMapping
    public Iterable<Property> getAllProperties() {
        return propertyService.getAllProperties();
    }

    /**
     * Actualiza una propiedad existente por su ID.
     * 
     * @param id El ID de la propiedad a actualizar.
     * @param property Objeto Property con los nuevos detalles de la propiedad.
     * @return ResponseEntity con la propiedad actualizada y el estado HTTP 200 (OK).
     */
    @PutMapping("/{id}")
    public ResponseEntity<Property> updateProperty(@PathVariable Long id, @Valid @RequestBody Property property) {
        Property updatedProperty = propertyService.updateProperty(id, property);
        return new ResponseEntity<>(updatedProperty, HttpStatus.OK);
    }

    /**
     * Elimina una propiedad por su ID.
     * 
     * @param id El ID de la propiedad a eliminar.
     * @return ResponseEntity con el estado HTTP 204 (NO CONTENT) si la eliminación fue exitosa.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProperty(@PathVariable Long id) {
        propertyService.deleteProperty(id);
        return ResponseEntity.noContent().build();
    }
}

