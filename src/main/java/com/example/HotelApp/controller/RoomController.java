package com.example.HotelApp.controller;

import com.example.HotelApp.service.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class RoomController {
    private final RoomService roomService;

    @GetMapping("/rooms")
    public String getAllRoomsByHotel(@RequestParam String hotelName) throws Exception {
        return roomService.getRooms(hotelName);
    }
}
