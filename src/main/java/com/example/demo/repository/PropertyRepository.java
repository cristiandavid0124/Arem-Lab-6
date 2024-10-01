package com.example.demo.repository;


import org.springframework.data.repository.CrudRepository;
import com.example.demo.model.Property;

public interface PropertyRepository extends CrudRepository<Property, Long> {
}
