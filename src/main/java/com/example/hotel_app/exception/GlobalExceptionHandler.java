package com.example.hotel_app.exception;

import com.example.hotel_app.exception.booking.BookingAlreadyCancelledException;
import com.example.hotel_app.exception.booking.BookingCannotBeCanceledException;
import com.example.hotel_app.exception.booking.BookingNotFoundException;
import com.example.hotel_app.exception.hotel.HotelsNotFoundException;
import com.example.hotel_app.exception.room.NoRoomsAvailableException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler({BookingNotFoundException.class})
    public ResponseEntity<Object> handleBookingNotFoundException(BookingNotFoundException e) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(e.getMessage());
    }

    @ExceptionHandler({BookingAlreadyCancelledException.class})
    public ResponseEntity<Object> handleBookingAlreadyCancelledException(BookingAlreadyCancelledException e) {
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(e.getMessage());
    }

    @ExceptionHandler({BookingCannotBeCanceledException.class})
    public ResponseEntity<Object> handleBookingCannotBeCanceledException(BookingCannotBeCanceledException e) {
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(e.getMessage());
    }

    @ExceptionHandler({HotelsNotFoundException.class})
    public ResponseEntity<Object> handleHotelsNotFoundException(HotelsNotFoundException e) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(e.getMessage());
    }

    @ExceptionHandler({NoRoomsAvailableException.class})
    public ResponseEntity<Object> handleNoRoomsAvailableException(NoRoomsAvailableException e) {
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(e.getMessage());
    }
}
