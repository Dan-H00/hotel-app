package com.example.hotel_app.controller;

import com.example.hotel_app.service.GeolocationService;
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
