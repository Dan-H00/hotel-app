package com.example.hotel_app.controller;

import com.example.hotel_app.dto.RoomDto;
import com.example.hotel_app.service.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class RoomController {
    private final RoomService roomService;

    @GetMapping("/rooms")
    public Page<RoomDto> getAllRoomsByHotel(@RequestParam String hotelName, @RequestParam Pageable pageable) throws Exception {
        return roomService.getRooms(hotelName, pageable);
    }
}
