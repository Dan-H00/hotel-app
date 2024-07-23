package com.example.hotel_app.service;

import com.example.hotel_app.dto.RoomDto;
import com.example.hotel_app.entity.Hotel;
import com.example.hotel_app.entity.Room;
import com.example.hotel_app.exception.room.NoRoomsAvailableException;
import com.example.hotel_app.mapper.RoomMapper;
import com.example.hotel_app.repository.HotelRepository;
import com.example.hotel_app.repository.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RoomService {
    private final RoomRepository roomRepository;
    private final HotelRepository hotelRepository;
    private final RoomMapper roomMapper;

    public List<RoomDto> getRooms(String hotelName) {
        Hotel hotel = hotelRepository.findByName(hotelName);
        List<Room> rooms = roomRepository.findAllByHotel(hotel);

        List<RoomDto> roomDtos = new ArrayList<>();

        for (Room room : rooms) {
            if (room.isAvailable()) {
                roomDtos.add(roomMapper.roomToRoomDto(room));
            }
        }

        if (!rooms.isEmpty()) {
            return roomDtos;
        } else {
            throw new NoRoomsAvailableException("No rooms available");
        }
    }
}
