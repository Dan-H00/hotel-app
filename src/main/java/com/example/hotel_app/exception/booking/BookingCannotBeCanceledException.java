package com.example.hotel_app.exception.booking;

public class BookingCannotBeCanceledException extends RuntimeException {
    public BookingCannotBeCanceledException(String message) {
        super(message);
    }
}
