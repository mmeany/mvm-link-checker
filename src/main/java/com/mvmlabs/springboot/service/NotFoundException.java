package com.mvmlabs.springboot.service;

public class NotFoundException extends Exception {

    private static final long serialVersionUID = -4162926343194605045L;

    public NotFoundException() {
        super();
    }

    public NotFoundException(final String message) {
        super(message);
    }
}
