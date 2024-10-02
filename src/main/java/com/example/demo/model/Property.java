package com.example.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/**
 * Representa una propiedad inmobiliaria en el sistema.
 * Contiene detalles como dirección, precio, tamaño y descripción.
 */
@Entity
public class Property {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id; // ID único de la propiedad

    @NotBlank(message = "Address is required")
    private String address; // Dirección de la propiedad

    @Min(value = 1, message = "Price must be greater than 0")
    private int price; // Precio de la propiedad

    @Min(value = 1, message = "Size must be greater than 0")
    private int size; // Tamaño de la propiedad en metros cuadrados

    @NotBlank(message = "Description is required")
    @Size(max = 500, message = "Description can't be longer than 500 characters")
    private String description; // Descripción de la propiedad

    // Constructor protegido para JPA
    protected Property() {}

    /**
     * Constructor para inicializar una nueva instancia de Property.
     * 
     * @param address Dirección de la propiedad.
     * @param price Precio de la propiedad.
     * @param size Tamaño de la propiedad.
     * @param description Descripción de la propiedad.
     */
    public Property(String address, int price, int size, String description) {
        this.address = address;
        this.price = price;
        this.size = size;
        this.description = description;
    }

    /**
     * Obtiene el ID de la propiedad.
     * 
     * @return ID de la propiedad.
     */
    public Long getId() {
        return id;
    }

    /**
     * Obtiene la dirección de la propiedad.
     * 
     * @return Dirección de la propiedad.
     */
    public String getAddress() {
        return address;
    }

    /**
     * Obtiene el precio de la propiedad.
     * 
     * @return Precio de la propiedad.
     */
    public int getPrice() {
        return price;
    }

    /**
     * Obtiene el tamaño de la propiedad.
     * 
     * @return Tamaño de la propiedad en metros cuadrados.
     */
    public int getSize() {
        return size;
    }

    /**
     * Obtiene la descripción de la propiedad.
     * 
     * @return Descripción de la propiedad.
     */
    public String getDescription() {
        return description;
    }
    
    /**
     * Retorna una representación en cadena de la propiedad.
     * 
     * @return Cadena que representa la propiedad.
     */
    @Override
    public String toString() {
        return String.format("Property[id=%d, address='%s', price=%d, size=%d, description='%s']",
                id, address, price, size, description);
    }

    /**
     * Establece el ID de la propiedad.
     * 
     * @param id El nuevo ID de la propiedad.
     */
    public void setId(Long id) {
        this.id = id;
    }
}


    public void setId(Long id) {
        this.id = id;
    }
}
