package com.example.hotel_app.controller;

import com.example.hotel_app.dto.BookingDtoInput;
import com.example.hotel_app.dto.BookingDtoOutput;
import com.example.hotel_app.service.BookingService;
import jakarta.websocket.server.PathParam;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class BookingController {
    private final BookingService bookingService;

    @PostMapping("/book")
    public ResponseEntity<List<BookingDtoOutput>> addBooking(@RequestBody BookingDtoInput bookingDtoInput) throws Exception {
        return ResponseEntity.ok(bookingService.add(bookingDtoInput));
    }

    @PostMapping("/cancel")
    public ResponseEntity<Void> cancelBooking(@RequestParam String name, @RequestParam int roomNumber) throws Exception {
        bookingService.cancel(name, roomNumber);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/bookings")
    public ResponseEntity<Page<BookingDtoOutput>> getBookings(@RequestParam int pageNo, @RequestParam int pageSize) throws Exception {
        return ResponseEntity.ok(bookingService.getBookings(pageNo, pageSize));
    }

    @GetMapping("/customer/{customerName}/bookings")
    public ResponseEntity<List<BookingDtoOutput>> getBookingsForCustomer(@PathVariable String customerName) {
        return ResponseEntity.ok(bookingService.getBookingsByCustomerName(customerName));
    }
}