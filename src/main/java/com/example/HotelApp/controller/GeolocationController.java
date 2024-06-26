package com.example.HotelApp.controller;

import com.example.HotelApp.service.GeolocationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
public class GeolocationController {
    private final GeolocationService geolocationService;

    @GetMapping("/latAndLong")
    private Double[] findLatAndLong() throws IOException, InterruptedException {
        return geolocationService.getCoordinates();
    }
}
