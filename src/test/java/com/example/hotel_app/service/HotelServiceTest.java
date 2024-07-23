package com.example.hotel_app.service;

import com.example.hotel_app.dto.HotelDto;
import com.example.hotel_app.entity.Hotel;
import com.example.hotel_app.mapper.HotelMapper;
import com.example.hotel_app.repository.HotelRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class HotelServiceTest {
    @Mock
    private HotelRepository hotelRepository;

    @Mock
    private GeolocationService geolocationService;

    @Mock
    private HotelMapper hotelMapper;

    @InjectMocks
    private HotelService hotelService;

    @Test
    public void testGetHotels() {
        Hotel hotel1 = Hotel.builder()
                .id(1)
                .name("Hotel 1")
                .longitude(23.598674125224626)
                .latitude(46.764654252624204)
                .build();

        Hotel hotel2 = Hotel.builder()
                .id(2)
                .name("Hotel 2")
                .longitude(23.605990381045697)
                .latitude(46.7522792440665)
                .build();

        HotelDto hotelDto1 = HotelDto.builder()
                .id(hotel1.getId())
                .name(hotel1.getName())
                .build();

        HotelDto hotelDto2 = HotelDto.builder()
                .id(hotel2.getId())
                .name(hotel2.getName())
                .build();

        when(hotelRepository.findAll()).thenReturn(List.of(hotel1, hotel2));
        when(geolocationService.getCoordinates()).thenReturn(new Double[]{46.7495, 23.4895});
        when(hotelMapper.hotelToHotelDto(hotel1)).thenReturn(hotelDto1);
        when(hotelMapper.hotelToHotelDto(hotel2)).thenReturn(hotelDto2);

        List<HotelDto> result = hotelService.getHotels(125000.0);
        assertEquals("Hotel 1 Hotel 2", result.get(0).getName() + " " + result.get(1).getName());

    }
}
