package com.example.HotelApp.service;

import com.example.HotelApp.entity.Hotel;
import com.example.HotelApp.entity.Room;
import com.example.HotelApp.exception.room.NoRoomsAvailableException;
import com.example.HotelApp.repository.HotelRepository;
import com.example.HotelApp.repository.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoomService {
    private final RoomRepository roomRepository;
    private final HotelRepository hotelRepository;

    public String getRooms(String hotelName) {
        Hotel hotel = hotelRepository.findByName(hotelName);
        List<Room> rooms = roomRepository.findAllByHotel(hotel);
        String result = "";
        int counter = 0;

        for (Room room : rooms) {
            if (room.isAvailable()) {
                counter++;
                result += "room: " + room.getRoomNumber() + " price: " + room.getPrice() + "\n";
            }
        }

        if (counter != 0) {
            return result;
        } else {
            throw new NoRoomsAvailableException("No rooms available");
        }
    }
}
