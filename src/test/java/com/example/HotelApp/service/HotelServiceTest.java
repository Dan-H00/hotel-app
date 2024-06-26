package com.example.HotelApp.service;

import com.example.HotelApp.entity.Hotel;
import com.example.HotelApp.model.Root;
import com.example.HotelApp.repository.HotelRepository;
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

    @InjectMocks
    private HotelService hotelService;

    private static final String API_KEY = "1df68f93a6e2462eba5ad07418197cb7";

    @Test
    public void testGetHotels() {
        String ip = "62.231.122.159";

        Root root = Root.builder()
                .latitude(46.7495)
                .longitude(23.4895)
                .build();

        Hotel hotel1 = Hotel.builder()
                .name("Hotel 1")
                .longitude(23.598674125224626)
                .latitude(46.764654252624204)
                .build();

        Hotel hotel2 = Hotel.builder()
                .name("Hotel 2")
                .longitude(23.605990381045697)
                .latitude(46.7522792440665)
                .build();

        when(hotelRepository.findAll()).thenReturn(List.of(hotel1, hotel2));
        when(geolocationService.getCoordinates()).thenReturn(new Double[]{root.getLatitude(), root.getLongitude()});

        String result = hotelService.getHotels(125000.0);

        assertEquals("Hotels found:\nHotel 1,\nHotel 2.", result);
    }
}
