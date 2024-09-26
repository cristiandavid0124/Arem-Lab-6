package com.example.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Property {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    private String address;
    private int price;
    private int size;
    private String description;

    // Constructor vacío
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
