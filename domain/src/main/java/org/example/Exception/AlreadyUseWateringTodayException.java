package org.example.Exception;

public class AlreadyUseWateringTodayException extends RuntimeException{
    public AlreadyUseWateringTodayException() {
        super("Plant Already Watered Today");
    }
}
