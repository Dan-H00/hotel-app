package com.example.hotel_app.controller;

import com.example.hotel_app.dto.HotelDto;
import com.example.hotel_app.service.HotelService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class HotelController {
    private final HotelService hotelService;

    @GetMapping("/hotels")
    public List<HotelDto> getHotelsByRadius(@RequestParam Double radius) throws Exception {
        return hotelService.getHotels(radius);
    }
}
