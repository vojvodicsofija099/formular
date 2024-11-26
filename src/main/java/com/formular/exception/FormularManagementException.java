package com.formular.exception;

public class FormularManagementException extends RuntimeException{

    private final String message;

    public FormularManagementException(String message) {
        this.message = message;
    }
}
