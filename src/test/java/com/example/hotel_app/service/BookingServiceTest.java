package com.example.hotel_app.service;

import com.example.hotel_app.dto.BookingDtoInput;
import com.example.hotel_app.dto.BookingDtoOutput;
import com.example.hotel_app.entity.Booking;
import com.example.hotel_app.entity.Hotel;
import com.example.hotel_app.entity.Room;
import com.example.hotel_app.mapper.BookingMapper;
import com.example.hotel_app.repository.BookingRepository;
import com.example.hotel_app.repository.RoomRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class BookingServiceTest {
    @Mock
    private RoomRepository roomRepository;

    @Mock
    private BookingRepository bookingRepository;

    @Mock
    private BookingMapper bookingMapper;

    @InjectMocks
    private BookingService bookingService;

    @Test
    public void testAdd() {
        BookingDtoInput bookingDtoInput = BookingDtoInput.builder()
                .roomNumber(new int[]{101, 102})
                .name("test")
                .checkInDate(LocalDate.parse("2024-06-25"))
                .checkInTime("10:00")
                .stayDuration(2)
                .build();

        Room room1 = Room.builder()
                .id(1)
                .roomNumber(101)
                .hotel(new Hotel())
                .price(101)
                .type(2)
                .build();

        Room room2 = Room.builder()
                .id(2)
                .roomNumber(102)
                .hotel(new Hotel())
                .price(102)
                .type(3)
                .build();

        Booking booking1 = Booking.builder()
                .id(1L)
                .name(bookingDtoInput.getName())
                .checkInDate(bookingDtoInput.getCheckInDate())
                .checkInTime(LocalTime.parse(bookingDtoInput.getCheckInTime()))
                .stayDuration(bookingDtoInput.getStayDuration())
                .room(room1)
                .build();

        Booking booking2 = Booking.builder()
                .id(2L)
                .name(bookingDtoInput.getName())
                .checkInDate(bookingDtoInput.getCheckInDate())
                .checkInTime(LocalTime.parse(bookingDtoInput.getCheckInTime()))
                .stayDuration(bookingDtoInput.getStayDuration())
                .room(room2)
                .build();

        BookingDtoOutput bookingDtoOutput1 = BookingDtoOutput.builder()
                .id(booking1.getId())
                .room(booking1.getRoom())
                .name(booking1.getName())
                .stayDuration(booking1.getStayDuration())
                .checkInDate(booking1.getCheckInDate())
                .checkInTime(booking1.getCheckInTime())
                .build();

        BookingDtoOutput bookingDtoOutput2 = BookingDtoOutput.builder()
                .id(booking2.getId())
                .room(booking2.getRoom())
                .name(booking2.getName())
                .stayDuration(booking2.getStayDuration())
                .checkInDate(booking2.getCheckInDate())
                .checkInTime(booking2.getCheckInTime())
                .build();

        when(roomRepository.findByRoomNumber(101))
                .thenReturn(room1);
        when(roomRepository.findByRoomNumber(102))
                .thenReturn(room2);

        when(bookingMapper.bookingDtoInputToBooking(bookingDtoInput))
                .thenReturn(booking1)
                .thenReturn(booking2);
        when(bookingMapper.bookingToBookingDtoOutput(booking1))
                .thenReturn(bookingDtoOutput1);
        when(bookingMapper.bookingToBookingDtoOutput(booking2))
                .thenReturn(bookingDtoOutput2);
        when(bookingRepository.save(booking1))
                .thenReturn(booking1);
        when(bookingRepository.save(booking2))
                .thenReturn(booking2);

        List<BookingDtoOutput> result = bookingService.add(bookingDtoInput);
        assertEquals("101 102", result.get(0).getRoom().getRoomNumber() + " " + result.get(1).getRoom().getRoomNumber());

        verify(bookingRepository).save(booking1);
        verify(bookingRepository).save(booking2);
        verify(roomRepository).save(room1);
        verify(roomRepository).save(room2);
        assertFalse(room1.isAvailable());
        assertFalse(room2.isAvailable());
    }

    @Test
    public void testCancel() {
        Booking booking = Booking.builder()
                .stayDuration(2)
                .name("test")
                .checkInDate(LocalDate.parse("2024-07-25"))
                .checkInTime(LocalTime.parse("10:00"))
                .room(Room.builder()
                        .type(2)
                        .price(100)
                        .roomNumber(101)
                        .hotel(new Hotel())
                        .build())
                .build();

        when(bookingRepository.findAllByName(booking.getName()))
                .thenReturn((List.of(booking)));

        bookingService.cancel(booking.getName(), booking.getRoom().getRoomNumber());

        verify(bookingRepository, times(1)).save(booking);
        verify(roomRepository).save(booking.getRoom());
        assertTrue(booking.isCancelled());
        assertTrue(booking.getRoom().isAvailable());
    }
}
