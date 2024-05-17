package com.example.HotelApp.controller;

import com.example.HotelApp.entity.Hotel;
import com.example.HotelApp.repository.HotelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.ArrayList;
import java.util.List;

@RestController
public class HotelController {
    @Autowired
    private HotelRepository hotelRepository;

    private final String SERVER_URL = "http://localhost:8080";

    private final WebClient webClient = WebClient.builder()
            .baseUrl(SERVER_URL)
            .build();

    @GetMapping("/hotels")
    public List<Hotel> getHotelsByRadius(@RequestParam Double radius) {
        List<Hotel> hotels = hotelRepository.findAll();
        List<Hotel> filteredHotels = new ArrayList<>();

        Double[] userCoords = this.webClient
                .get()
                .uri(uriBuilder -> uriBuilder.path("/latAndLong").build())
                .retrieve()
                .bodyToMono(Double[].class)
                .block();

        Double[] userPos = convertCoordinates(userCoords[0], userCoords[1]);

        for (Hotel hotel : hotels) {
            Double[] hotelPos = convertCoordinates(hotel.getLatitude(), hotel.getLongitude());
            double distance = Math.sqrt(Math.pow((hotelPos[0] - userPos[0]), 2) + Math.pow((hotelPos[1] - userPos[1]), 2));
            System.out.println(distance);
            if (distance <= radius) {
                filteredHotels.add(hotel);
            }
        }

        if (!filteredHotels.isEmpty()) {
            return filteredHotels;
        }
        else {
            throw new RuntimeException("No hotels in radius!");
        }
    }

    private Double[] convertCoordinates(Double latitude, Double longitude) {
        double mx = longitude / 180 * 20037508.34;
        double my = Math.log(Math.tan((90 + latitude) * Math.PI / 360)) / (Math.PI / 180);
        my = my * 20037508.34 / 180;

        return new Double[]{mx, my};
    }
}
