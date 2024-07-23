package com.example.hotel_app.service;

import com.example.hotel_app.dto.HotelDto;
import com.example.hotel_app.entity.Hotel;
import com.example.hotel_app.exception.hotel.HotelsNotFoundException;
import com.example.hotel_app.mapper.HotelMapper;
import com.example.hotel_app.repository.HotelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class HotelService {
    private final HotelRepository hotelRepository;
    private final GeolocationService geolocationService;
    private final HotelMapper hotelMapper;

    public List<HotelDto> getHotels(Double radius) {
        List<Hotel> hotels = hotelRepository.findAll();
        List<HotelDto> hotelDtos = new ArrayList<>();

        Double[] userCoords = geolocationService.getCoordinates();

        Double[] userPos = convertCoordinates(userCoords[0], userCoords[1]);

        for (Hotel hotel : hotels) {
            Double[] hotelPos = convertCoordinates(hotel.getLatitude(), hotel.getLongitude());
            double distance = Math.sqrt(Math.pow((hotelPos[0] - userPos[0]), 2) + Math.pow((hotelPos[1] - userPos[1]), 2));
            if (distance <= radius) {
                hotelDtos.add(hotelMapper.hotelToHotelDto(hotel));
            }
        }

        if (!hotelDtos.isEmpty()) {
            return hotelDtos;
        } else {
            throw new HotelsNotFoundException("Hotels not found");
        }
    }

    private Double[] convertCoordinates(Double latitude, Double longitude) {
        double mx = longitude / 180 * 20037508.34;
        double my = Math.log(Math.tan((90 + latitude) * Math.PI / 360)) / (Math.PI / 180);
        my = my * 20037508.34 / 180;

        return new Double[]{mx, my};
    }
}
