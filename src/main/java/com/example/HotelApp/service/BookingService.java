package com.example.HotelApp.service;

import com.example.HotelApp.dto.BookingDto;
import com.example.HotelApp.entity.Booking;
import com.example.HotelApp.entity.Room;
import com.example.HotelApp.exception.booking.BookingAlreadyCancelledException;
import com.example.HotelApp.exception.booking.BookingCannotBeCanceledException;
import com.example.HotelApp.exception.booking.BookingNotFoundException;
import com.example.HotelApp.mapper.BookingMapper;
import com.example.HotelApp.repository.BookingRepository;
import com.example.HotelApp.repository.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BookingService {
    private final BookingRepository bookingRepository;
    private final RoomRepository roomRepository;
    private final BookingMapper bookingMapper;

    public String add(BookingDto bookingDto) {
        for (int roomNr : bookingDto.getRoomNumber()) {
            Room room = roomRepository.findByRoomNumber(roomNr);

            Booking booking = bookingMapper.bookingDtoToBooking(bookingDto);
            bookingRepository.save(booking);

            room.setAvailable(false);
            roomRepository.save(room);
        }

        return "Booking successfully added";
    }

    public String cancel(String name, int roomNumber) {
        List<Booking> bookings = bookingRepository.findAllByName(name);

        if (bookings.isEmpty()) {
            throw new BookingNotFoundException("Booking with name " + name + " not found");
        }

        for (Booking booking : bookings) {
            if (booking.getRoom().getRoomNumber() == roomNumber) {
                if (booking.isCancelled()) {
                    throw new BookingAlreadyCancelledException("Booking already cancelled");
                }
                if (!checkCancellability(booking)) {
                    throw new BookingCannotBeCanceledException("Booking cannot be cancelled");
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
