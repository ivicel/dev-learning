package com.springinaction.pizza.service;

public class CustomerNotFoundException extends RuntimeException {
    private static final long serialVersionUID = -8021326258547883658L;

    public CustomerNotFoundException() {
    }

    public CustomerNotFoundException(String message) {
        super(message);
    }
}
