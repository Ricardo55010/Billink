package com.example.billink.Exceptions;

public class NoValidIdempotencyKey extends  RuntimeException {
    public NoValidIdempotencyKey(String errorMessage) {
        super(errorMessage);
    }
}
