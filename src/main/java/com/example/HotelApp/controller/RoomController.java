package com.example.HotelApp.controller;

import com.example.HotelApp.entity.Hotel;
import com.example.HotelApp.entity.Room;
import com.example.HotelApp.repository.HotelRepository;
import com.example.HotelApp.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class RoomController {
    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private HotelRepository hotelRepository;

    @GetMapping("/rooms")
    public String getAllRoomsByHotel(@RequestParam String hotelName) {
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
            return "No rooms available";
        }
    }
}
