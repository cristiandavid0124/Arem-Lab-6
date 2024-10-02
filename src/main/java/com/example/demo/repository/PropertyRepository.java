package com.example.demo.repository;

import org.springframework.data.repository.CrudRepository;
import com.example.demo.model.Property;

/**
 * Interfaz de repositorio para la entidad Property.
 * Proporciona operaciones CRUD para gestionar propiedades en la base de datos.
 */
public interface PropertyRepository extends CrudRepository<Property, Long> {

}
