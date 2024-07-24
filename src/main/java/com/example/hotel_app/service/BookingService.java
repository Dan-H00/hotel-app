package com.example.hotel_app.service;

import com.example.hotel_app.dto.BookingDtoInput;
import com.example.hotel_app.dto.BookingDtoOutput;
import com.example.hotel_app.entity.Booking;
import com.example.hotel_app.entity.Room;
import com.example.hotel_app.exception.booking.BookingAlreadyCancelledException;
import com.example.hotel_app.exception.booking.BookingCannotBeCanceledException;
import com.example.hotel_app.exception.booking.BookingNotFoundException;
import com.example.hotel_app.mapper.BookingMapper;
import com.example.hotel_app.repository.BookingRepository;
import com.example.hotel_app.repository.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BookingService {
    private final BookingRepository bookingRepository;
    private final RoomRepository roomRepository;
    private final BookingMapper bookingMapper;

    public List<BookingDtoOutput> add(BookingDtoInput bookingDtoInput) {
        List<BookingDtoOutput> result = new ArrayList<>();

        for (int roomNr : bookingDtoInput.getRoomNumber()) {
            Room room = roomRepository.findByRoomNumber(roomNr);

            Booking booking = bookingMapper.bookingDtoInputToBooking(bookingDtoInput);
            Booking outputBooking = bookingRepository.save(booking);

            result.add(bookingMapper.bookingToBookingDtoOutput(outputBooking));

            room.setAvailable(false);
            roomRepository.save(room);
        }

        return result;
    }

    public void cancel(String name, int roomNumber) {
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
