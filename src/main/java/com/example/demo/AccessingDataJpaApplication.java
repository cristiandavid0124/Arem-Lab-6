package com.example.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import java.util.Optional;

import com.example.demo.model.Property;
import com.example.demo.repository.PropertyRepository;

@SpringBootApplication
public class AccessingDataJpaApplication {

    private static final Logger log = LoggerFactory.getLogger(AccessingDataJpaApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(AccessingDataJpaApplication.class, args);
    }

    @Bean
    public CommandLineRunner demo(PropertyRepository repository) {
        return (args) -> {


            repository.save(new Property("123 Main St", 300000, 1500, "Beautiful home"));
            repository.save(new Property("456 Elm St", 250000, 1200, "Cozy apartment"));
            repository.save(new Property("789 Oak St", 350000, 1800, "Spacious villa"));
            repository.save(new Property("101 Pine St", 400000, 2000, "Modern house"));
            repository.save(new Property("202 Maple St", 280000, 1400, "Charming cottage"));

            log.info("Properties found with findAll():");
            log.info("-------------------------------");
            repository.findAll().forEach(property -> {
                log.info(property.toString());
            });
            log.info("");
            
            Optional<Property> propertyOptional = repository.findById(1L);
            log.info("Property found with findById(1L):");
            log.info("--------------------------------");
            if (propertyOptional.isPresent()) {
                log.info(propertyOptional.get().toString());
            } else {
                log.info("No property found with ID 1");
            }
            log.info("");
        };
    }
}
