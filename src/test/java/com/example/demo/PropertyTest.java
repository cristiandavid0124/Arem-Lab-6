package com.example.demo;


import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.example.demo.model.*;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class PropertyTest {

    private Validator validator;

    @BeforeEach
    public void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    public void testValidProperty() {
        Property property = new Property("123 Main St", 250000, 1200, "A beautiful property");

        Set<ConstraintViolation<Property>> violations = validator.validate(property);

        assertTrue(violations.isEmpty(), "Property should be valid");
    }

    @Test
    public void testAddressNotBlank() {
        Property property = new Property("", 250000, 1200, "A beautiful property");

        Set<ConstraintViolation<Property>> violations = validator.validate(property);

        assertFalse(violations.isEmpty(), "Property address should not be blank");
        assertEquals("Address is required", violations.iterator().next().getMessage());
    }

    @Test
    public void testPriceMustBeGreaterThanZero() {
        Property property = new Property("123 Main St", 0, 1200, "A beautiful property");

        Set<ConstraintViolation<Property>> violations = validator.validate(property);

        assertFalse(violations.isEmpty(), "Property price must be greater than 0");
        assertEquals("Price must be greater than 0", violations.iterator().next().getMessage());
    }

    @Test
    public void testSizeMustBeGreaterThanZero() {
        Property property = new Property("123 Main St", 250000, 0, "A beautiful property");

        Set<ConstraintViolation<Property>> violations = validator.validate(property);

        assertFalse(violations.isEmpty(), "Property size must be greater than 0");
        assertEquals("Size must be greater than 0", violations.iterator().next().getMessage());
    }

    @Test
    public void testDescriptionNotBlank() {
        Property property = new Property("123 Main St", 250000, 1200, "");

        Set<ConstraintViolation<Property>> violations = validator.validate(property);

        assertFalse(violations.isEmpty(), "Property description should not be blank");
        assertEquals("Description is required", violations.iterator().next().getMessage());
    }

    @Test
    public void testDescriptionMaxLength() {
        String longDescription = "A".repeat(501); // Generar una descripción de 501 caracteres
        Property property = new Property("123 Main St", 250000, 1200, longDescription);

        Set<ConstraintViolation<Property>> violations = validator.validate(property);

        assertFalse(violations.isEmpty(), "Property description should not exceed 500 characters");
        assertEquals("Description can't be longer than 500 characters", violations.iterator().next().getMessage());
    }
}
