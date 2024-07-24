package com.example.hotel_app.exception.room;

public class NoRoomsAvailableException extends RuntimeException {
    public NoRoomsAvailableException(String message) {
        super(message);
    }
}
