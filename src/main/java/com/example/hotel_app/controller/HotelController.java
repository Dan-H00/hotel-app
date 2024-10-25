package com.example.hotel_app.controller;

import com.example.hotel_app.dto.HotelDto;
import com.example.hotel_app.service.HotelService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.data.domain.Pageable;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class HotelController {
    private final HotelService hotelService;

    @GetMapping("/hotels")
    public ResponseEntity<Page<HotelDto>> getHotelsByRadius(@RequestParam Double radius, @RequestParam int pageNo, @RequestParam int pageSize) throws Exception {
        return ResponseEntity.ok(hotelService.getHotels(radius, pageNo, pageSize));
    }
}
