package com.example.HotelApp.exception.hotel;

public class HotelsNotFoundException extends RuntimeException {
    public HotelsNotFoundException(String message) {
        super(message);
    }
}
