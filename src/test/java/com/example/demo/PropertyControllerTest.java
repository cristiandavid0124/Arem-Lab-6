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

/**
 * Clase de prueba para el controlador de propiedades.
 * 
 * Esta clase contiene pruebas unitarias para la clase {@link PropertyController},
 * utilizando el marco de pruebas JUnit y Mockito para simular el comportamiento
 * de las dependencias. Las pruebas aseguran que el controlador maneje correctamente
 * las operaciones CRUD relacionadas con las propiedades.
 * 
 * @see PropertyController
 * @see PropertyService
 * @see Property
 */
class PropertyControllerTest {

    /**
     * Instancia del controlador de propiedades a probar.
     */
    @InjectMocks
    private PropertyController propertyController;

    /**
     * Servicio de propiedades simulado, utilizado como dependencia del controlador.
     */
    @Mock
    private PropertyService propertyService;

    /**
     * Propiedad de prueba que se utilizará en varias pruebas.
     */
    private Property property;

    /**
     * Método de configuración que se ejecuta antes de cada prueba.
     * Inicializa los mocks y crea una instancia de la propiedad de prueba.
     */
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        property = new Property("123 Main St", 250000, 1500, "Beautiful home");
    }

    /**
     * Prueba que verifica la creación de una propiedad.
     * 
     * Se simula el comportamiento del servicio para devolver la propiedad creada
     * y se valida que la respuesta del controlador sea correcta.
     */
    @Test
    void createProperty_ShouldReturnCreatedProperty() {
        when(propertyService.createProperty(any(Property.class))).thenReturn(property);

        ResponseEntity<Property> response = propertyController.createProperty(property);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(property, response.getBody());
    }

    /**
     * Prueba que verifica la obtención de una propiedad por su ID.
     * 
     * Se simula la devolución de una propiedad existente y se valida
     * que la respuesta del controlador contenga la propiedad correcta.
     */
    @Test
    void getPropertyById_ShouldReturnProperty() {
        when(propertyService.getPropertyById(anyLong())).thenReturn(Optional.of(property));

        ResponseEntity<Property> response = propertyController.getPropertyById(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(property, response.getBody());
    }

    /**
     * Prueba que verifica el comportamiento del controlador al intentar obtener
     * una propiedad que no existe.
     * 
     * Se simula la ausencia de la propiedad y se valida que la respuesta sea
     * un estado NOT_FOUND.
     */
    @Test
    void getPropertyById_ShouldReturnNotFound() {
        when(propertyService.getPropertyById(anyLong())).thenReturn(Optional.empty());

        ResponseEntity<Property> response = propertyController.getPropertyById(1L);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    /**
     * Prueba que verifica la obtención de todas las propiedades.
     * 
     * Se simula el servicio para devolver una lista de propiedades y se valida
     * que la respuesta contenga la lista correcta.
     */
    @Test
    void getAllProperties_ShouldReturnListOfProperties() {
        Iterable<Property> properties = List.of(property);
        when(propertyService.getAllProperties()).thenReturn(properties);

        Iterable<Property> response = propertyController.getAllProperties();

        assertNotNull(response);
        assertEquals(1, ((Collection<Property>) response).size());
    }

    /**
     * Prueba que verifica la actualización de una propiedad existente.
     * 
     * Se simula el comportamiento del servicio para devolver la propiedad
     * actualizada y se valida que la respuesta del controlador sea correcta.
     */
    @Test
    void updateProperty_ShouldReturnUpdatedProperty() {
        when(propertyService.updateProperty(anyLong(), any(Property.class))).thenReturn(property);

        ResponseEntity<Property> response = propertyController.updateProperty(1L, property);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(property, response.getBody());
    }

    /**
     * Prueba que verifica la eliminación de una propiedad.
     * 
     * Se simula la eliminación de una propiedad en el servicio y se valida
     * que la respuesta del controlador sea un estado NO_CONTENT.
     */
    @Test
    void deleteProperty_ShouldReturnNoContent() {
        doNothing().when(propertyService).deleteProperty(anyLong());

        ResponseEntity<Void> response = propertyController.deleteProperty(1L);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }
}
