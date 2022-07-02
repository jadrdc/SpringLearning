package com.agustin.demo.exceptions;

public class PaymentMethodNotFoundException extends RuntimeException {

    public PaymentMethodNotFoundException(Long id) {
        super("Could not find employee " + id);
    }
}