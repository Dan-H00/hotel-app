package com.example.hotel_app.exception.booking;

public class BookingNotFoundException extends RuntimeException {
    public BookingNotFoundException(String message) {
        super(message);
    }
}
