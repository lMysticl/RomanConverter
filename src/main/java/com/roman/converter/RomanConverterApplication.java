package com.roman.converter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Roman Numeral Converter Application
 * 
 * A Spring Boot application that provides a web interface and REST API
 * for converting between Roman and Arabic numerals.
 * 
 * Supports canonical Roman numerals for integers from 1 through 3999.
 * The application provides a static web interface and REST API endpoints.
 * 
 * @see com.roman.converter.controller.RomanController
 * @see com.roman.converter.service.RomanService
 */
@SpringBootApplication
public class RomanConverterApplication {
    
    /**
     * Main entry point for the application.
     * Starts the Spring Boot application and initializes all components.
     *
     * @param args Command line arguments
     */
    public static void main(String[] args) {
        SpringApplication.run(RomanConverterApplication.class, args);
    }
}
