package com.example.hotel_app.service;

import com.example.hotel_app.dto.RoomDto;
import com.example.hotel_app.entity.Hotel;
import com.example.hotel_app.entity.Room;
import com.example.hotel_app.exception.hotel.HotelsNotFoundException;
import com.example.hotel_app.exception.room.NoRoomsAvailableException;
import com.example.hotel_app.mapper.RoomMapper;
import com.example.hotel_app.repository.HotelRepository;
import com.example.hotel_app.repository.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RoomService {
    private final RoomRepository roomRepository;
    private final HotelRepository hotelRepository;
    private final RoomMapper roomMapper;

    public Page<RoomDto> getRooms(String hotelName, int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        Hotel hotel = hotelRepository.findByName(hotelName);

        if (hotel == null) {
            throw new HotelsNotFoundException("Hotel not found");
        }

        Page<Room> rooms = roomRepository.findAllByHotel(hotel, pageable);
        List<RoomDto> result = new ArrayList<>();

//        for (Room room : rooms) {
//            if (room.isAvailable()) {
//                result.add(roomMapper.roomToRoomDto(room));
//            }
//        }

        if (result.isEmpty()) {
            throw new NoRoomsAvailableException("No rooms available");
        }

        return new PageImpl<>(result);
    }
}
