package com.example.hotel_app.controller;

import com.example.hotel_app.dto.BookingDtoInput;
import com.example.hotel_app.dto.BookingDtoOutput;
import com.example.hotel_app.service.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class BookingController {
    private final BookingService bookingService;

    @PostMapping("/book")
    public List<BookingDtoOutput> addBooking(@RequestBody BookingDtoInput bookingDtoInput) throws Exception {
        return bookingService.add(bookingDtoInput);
    }

    @PostMapping("/cancel")
    public void cancelBooking(@RequestParam String name, @RequestParam int roomNumber) throws Exception {
        bookingService.cancel(name, roomNumber);
    }
}