package com.example.infrastructure.Exception;

public class NotFoundException extends RuntimeException{
    public NotFoundException() {
        super("Not Found");
    }
}
