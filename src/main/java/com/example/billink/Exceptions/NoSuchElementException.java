package com.example.billink.Exceptions;

public class NoSuchElementException extends  RuntimeException {
    public NoSuchElementException(String errorMessage) {
        super(errorMessage);
    }
}
