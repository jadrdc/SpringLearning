package com.agustin.demo.exceptions;

public class AddressNotFoundException extends RuntimeException {

    public AddressNotFoundException(Long id) {
        super("Could not find employee " + id);
    }
}