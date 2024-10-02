package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import com.example.demo.model.Property;
import com.example.demo.service.PropertyService;
import org.junit.jupiter.api.BeforeEach;
import com.example.demo.controller.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

class PropertyControllerTest {

    @InjectMocks
    private PropertyController propertyController;

    @Mock
    private PropertyService propertyService;

    private Property property;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        property = new Property("123 Main St", 250000, 1500, "Beautiful home");
    }

    @Test
    void createProperty_ShouldReturnCreatedProperty() {
        when(propertyService.createProperty(any(Property.class))).thenReturn(property);

        ResponseEntity<Property> response = propertyController.createProperty(property);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(property, response.getBody());
    }

    @Test
    void getPropertyById_ShouldReturnProperty() {
        when(propertyService.getPropertyById(anyLong())).thenReturn(Optional.of(property));

        ResponseEntity<Property> response = propertyController.getPropertyById(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(property, response.getBody());
    }

    @Test
    void getPropertyById_ShouldReturnNotFound() {
        when(propertyService.getPropertyById(anyLong())).thenReturn(Optional.empty());

        ResponseEntity<Property> response = propertyController.getPropertyById(1L);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void getAllProperties_ShouldReturnListOfProperties() {
        Iterable<Property> properties = List.of(property);
        when(propertyService.getAllProperties()).thenReturn(properties);

        Iterable<Property> response = propertyController.getAllProperties();

        assertNotNull(response);
        assertEquals(1, ((Collection<Property>) response).size());
    }

    @Test
    void updateProperty_ShouldReturnUpdatedProperty() {
        when(propertyService.updateProperty(anyLong(), any(Property.class))).thenReturn(property);

        ResponseEntity<Property> response = propertyController.updateProperty(1L, property);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(property, response.getBody());
    }

    @Test
    void deleteProperty_ShouldReturnNoContent() {
        doNothing().when(propertyService).deleteProperty(anyLong());

        ResponseEntity<Void> response = propertyController.deleteProperty(1L);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }
}