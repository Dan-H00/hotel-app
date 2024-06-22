package com.example.HotelApp.controller;

import com.example.HotelApp.service.HotelService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class HotelController {
    private final HotelService hotelService;

    @GetMapping("/hotels")
    public String getHotelsByRadius(@RequestParam Double radius) {
        return hotelService.getHotels(radius);
    }
}
