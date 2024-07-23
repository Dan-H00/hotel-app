package com.example.hotel_app.exception.hotel;

public class HotelsNotFoundException extends RuntimeException {
    public HotelsNotFoundException(String message) {
        super(message);
    }
}
