package com.example.HotelApp.service;

import com.example.HotelApp.entity.Hotel;
import com.example.HotelApp.entity.Room;
import com.example.HotelApp.repository.HotelRepository;
import com.example.HotelApp.repository.RoomRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class RoomServiceTest {
    @Mock
    RoomRepository roomRepository;

    @Mock
    HotelRepository hotelRepository;

    @InjectMocks
    RoomService roomService;

    @Test
    public void testGetRooms() {
        Hotel hotel = Hotel.builder()
                .name("Test Hotel")
                .latitude(100)
                .longitude(200)
                .build();

        Room room1 = Room.builder()
                .roomNumber(101)
                .hotel(hotel)
                .price(101)
                .isAvailable(true)
                .build();

        Room room2 = Room.builder()
                .roomNumber(102)
                .hotel(hotel)
                .price(102)
                .isAvailable(true)
                .build();

        when(hotelRepository.findByName(hotel.getName())).thenReturn(hotel);
        when(roomRepository.findAllByHotel(hotel)).thenReturn(List.of(room1, room2));

        String result = roomService.getRooms(hotel.getName());

        assertEquals("room: 101 price: 101.0\nroom: 102 price: 102.0\n", result);
    }
}
