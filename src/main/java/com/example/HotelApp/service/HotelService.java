package com.example.HotelApp.service;

import com.example.HotelApp.entity.Hotel;
import com.example.HotelApp.repository.HotelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class HotelService {
    private final HotelRepository hotelRepository;
    private final GeolocationService geolocationService;

    public String getHotels(Double radius) {
        List<Hotel> hotels = hotelRepository.findAll();
        String returnString = "Hotels found:";
        int counter = 0;

        Double[] userCoords = geolocationService.getCoordinates();

        Double[] userPos = convertCoordinates(userCoords[0], userCoords[1]);

        for (Hotel hotel : hotels) {
            Double[] hotelPos = convertCoordinates(hotel.getLatitude(), hotel.getLongitude());
            double distance = Math.sqrt(Math.pow((hotelPos[0] - userPos[0]), 2) + Math.pow((hotelPos[1] - userPos[1]), 2));
            System.out.println(distance);
            if (distance <= radius) {
                counter++;
                returnString = returnString + "\n" + hotel.getName() + ",";
            }
        }

        if (counter != 0) {
            returnString = removeLastCharRegexOptional(returnString);
            return returnString;
        } else {
            return "Hotels not found";
        }
    }

    private Double[] convertCoordinates(Double latitude, Double longitude) {
        double mx = longitude / 180 * 20037508.34;
        double my = Math.log(Math.tan((90 + latitude) * Math.PI / 360)) / (Math.PI / 180);
        my = my * 20037508.34 / 180;

        return new Double[]{mx, my};
    }

    public static String removeLastCharRegexOptional(String s) {
        return Optional.ofNullable(s)
                .map(str -> str.replaceAll(".$", "."))
                .orElse(s);
    }
}
