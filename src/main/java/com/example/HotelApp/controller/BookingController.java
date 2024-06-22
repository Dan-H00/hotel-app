package com.example.HotelApp.controller;

import com.example.HotelApp.entity.Booking;
import com.example.HotelApp.entity.Room;
import com.example.HotelApp.repository.BookingRepository;
import com.example.HotelApp.repository.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class BookingController {
    private final BookingRepository bookingRepository;
    private final RoomRepository roomRepository;

    @PostMapping("/book")
    public String addBooking(@RequestParam int[] roomNumber, @RequestParam String name, @RequestParam String date, @RequestParam int stayDays, @RequestParam String time) {
        for (int roomNr : roomNumber) {
            Room room = roomRepository.findByRoomNumber(roomNr);
            LocalDate checkInDate = LocalDate.parse(date, DateTimeFormatter.BASIC_ISO_DATE);
            LocalTime checkInTime = LocalTime.parse(time, DateTimeFormatter.ISO_TIME);

            Booking booking = new Booking().builder()
                    .name(name)
                    .room(room)
                    .checkInDate(checkInDate)
                    .stayDuration(stayDays)
                    .checkInTime(checkInTime)
                    .build();

            bookingRepository.save(booking);

            room.setAvailable(false);
            roomRepository.save(room);
        }

        return "Booking successfully added";
    }

    @PostMapping("/cancel")
    public String cancelBooking(@RequestParam String name, @RequestParam int roomNumber) {
        List<Booking> bookings = bookingRepository.findAllByName(name);

        if (bookings.isEmpty()) {
            return "Bookings not found on name: " + name;
        }

        for (Booking booking : bookings) {
            if (booking.getRoom().getRoomNumber() == roomNumber) {
                if (booking.isCancelled()) {
                    return "Booking already cancelled";
                }
                if (!checkCancellability(booking)) {
                    return "Booking cannot be cancelled";
                }
                booking.getRoom().setAvailable(true);
                booking.setCancelled(true);
                bookingRepository.save(booking);
                roomRepository.save(booking.getRoom());
            }
        }

        return "Booking cancelled";
    }

    private boolean checkCancellability(Booking booking) {
        if (booking.getCheckInDate().isAfter(LocalDate.now())) {
            return true;
        }
        else if (booking.getCheckInDate().isEqual(LocalDate.now())) {
            return LocalTime.now().getHour() - booking.getCheckInTime().getHour() >= 2;
        }
        return false;
    }
}