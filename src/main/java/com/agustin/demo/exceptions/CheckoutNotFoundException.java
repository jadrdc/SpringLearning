package com.agustin.demo.exceptions;

public class CheckoutNotFoundException extends RuntimeException {

    public CheckoutNotFoundException(Long id) {
        super("Could not find Checkout " + id);
    }
}