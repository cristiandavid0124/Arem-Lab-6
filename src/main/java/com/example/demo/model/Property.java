package com.example.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
public class Property {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank(message = "Address is required")
    private String address;

    @Min(value = 1, message = "Price must be greater than 0")
    private int price;

    @Min(value = 1, message = "Size must be greater than 0")
    private int size;

    @NotBlank(message = "Description is required")
    @Size(max = 500, message = "Description can't be longer than 500 characters")
    private String description;

    protected Property() {}

    public Property(String address, int price, int size, String description) {
        this.address = address;
        this.price = price;
        this.size = size;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public String getAddress() {
        return address;
    }

    public int getPrice() {
        return price;
    }

    public int getSize() {
        return size;
    }

    public String getDescription() {
        return description;
    }
    
    @Override
    public String toString() {
        return String.format("Property[id=%d, address='%s', price=%d, size=%d, description='%s']",
                id, address, price, size, description);
    }

    public void setId(Long id) {
        this.id = id;
    }
}
