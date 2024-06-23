package com.example.HotelApp.controller;

import com.example.HotelApp.dto.BookingDto;
import com.example.HotelApp.entity.Booking;
import com.example.HotelApp.mapper.BookingMapper;
import com.example.HotelApp.service.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class BookingController {
    private final BookingService bookingService;

    @PostMapping("/book")
    public String addBooking(@RequestBody BookingDto bookingDto) {
        return bookingService.add(bookingDto.getRoomNumber(), bookingDto.getName(), bookingDto.getDate(), bookingDto.getStayDays(), bookingDto.getTime());
    }

    @PostMapping("/cancel")
    public String cancelBooking(@RequestParam String name, @RequestParam int roomNumber) {
        return bookingService.cancel(name, roomNumber);
    }
}