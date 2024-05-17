package com.example.HotelApp.controller;

import com.example.HotelApp.entity.Booking;
import com.example.HotelApp.entity.Room;
import com.example.HotelApp.repository.BookingRepository;
import com.example.HotelApp.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BookingController {
    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private RoomRepository roomRepository;

    @PostMapping("/book")
    public String addBooking(@RequestParam int roomNumber, @RequestParam String name) {
        Room room = roomRepository.findByRoomNumber(roomNumber);
        room.setAvailable(false);
        roomRepository.save(room);

        Booking booking = new Booking().builder()
                .name(name)
                .room(room)
                .build();

        bookingRepository.save(booking);

        return "Booking successfully added";
    }
}
