package com.example.HotelApp.exception.booking;

public class BookingCannotBeCanceledException extends RuntimeException {
    public BookingCannotBeCanceledException(String message) {
        super(message);
    }
}
