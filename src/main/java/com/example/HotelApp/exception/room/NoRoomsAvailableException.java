package com.example.HotelApp.exception.room;

public class NoRoomsAvailableException extends RuntimeException {
    public NoRoomsAvailableException(String message) {
        super(message);
    }
}
