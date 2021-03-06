package com.agustin.demo.exceptions;

public class ProductNotFoundException extends RuntimeException {

    public ProductNotFoundException(Long id) {
        super("Could not find employee " + id);
    }
}