package com.example.hotel_app.service;

import com.example.hotel_app.dto.RoomDto;
import com.example.hotel_app.entity.Hotel;
import com.example.hotel_app.entity.Room;
import com.example.hotel_app.mapper.RoomMapper;
import com.example.hotel_app.repository.HotelRepository;
import com.example.hotel_app.repository.RoomRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class RoomServiceTest {
    @Mock
    private RoomRepository roomRepository;

    @Mock
    private HotelRepository hotelRepository;

    @Mock
    private RoomMapper roomMapper;

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
                .id(1)
                .roomNumber(101)
                .hotel(hotel)
                .pricePerNight(101)
//                .isAvailable(true)
                .build();

        RoomDto roomDto1 = RoomDto.builder()
                .id(room1.getId())
                .roomNumber(room1.getRoomNumber())
                .price(room1.getPricePerNight())
                .build();

        Room room2 = Room.builder()
                .id(2)
                .roomNumber(102)
                .hotel(hotel)
                .pricePerNight(102)
//                .isAvailable(true)
                .build();

        RoomDto roomDto2 = RoomDto.builder()
                .id(room2.getId())
                .roomNumber(room2.getRoomNumber())
                .price(room2.getPricePerNight())
                .build();

        when(hotelRepository.findByName(hotel.getName())).thenReturn(hotel);
        when(roomRepository.findAllByHotel(hotel, PageRequest.of(0, 2))).thenReturn(new PageImpl<>(List.of(room1, room2)));
        when(roomMapper.roomToRoomDto(room1)).thenReturn(roomDto1);
        when(roomMapper.roomToRoomDto(room2)).thenReturn(roomDto2);

        Page<RoomDto> result = roomService.getRooms(hotel.getName(), 0, 2);
        assertEquals("101 102", result.getContent().getFirst().getRoomNumber() + " " + result.getContent().get(1).getRoomNumber());

    }
}
