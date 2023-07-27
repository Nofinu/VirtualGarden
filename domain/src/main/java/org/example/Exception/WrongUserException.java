package org.example.Exception;

public class WrongUserException extends RuntimeException{
    public WrongUserException() {
        super("Wrong User");
    }
}
