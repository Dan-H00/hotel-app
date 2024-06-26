package com.example.HotelApp.service;

import com.example.HotelApp.dto.BookingDto;
import com.example.HotelApp.entity.Booking;
import com.example.HotelApp.entity.Hotel;
import com.example.HotelApp.entity.Room;
import com.example.HotelApp.mapper.BookingMapper;
import com.example.HotelApp.repository.BookingRepository;
import com.example.HotelApp.repository.RoomRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
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
        BookingDto bookingDto = BookingDto.builder()
                .roomNumber(new int[]{101, 102})
                .name("test")
                .checkInDate(LocalDate.parse("2024-06-25"))
                .checkInTime("10:00")
                .stayDuration(2)
                .build();

        Room room1 = Room.builder()
                .roomNumber(101)
                .hotel(new Hotel())
                .price(101)
                .type(2)
                .build();

        Room room2 = Room.builder()
                .roomNumber(102)
                .hotel(new Hotel())
                .price(102)
                .type(3)
                .build();

        Booking booking = new Booking();

        when(roomRepository.findByRoomNumber(101)).thenReturn(room1);
        when(roomRepository.findByRoomNumber(102)).thenReturn(room2);
        when(bookingMapper.bookingDtoToBooking(bookingDto)).thenReturn(booking);

        String result = bookingService.add(bookingDto);

        assertEquals("Booking successfully added", result);
        verify(bookingRepository, times(2)).save(booking);
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

        when(bookingRepository.findAllByName(booking.getName())).thenReturn((List.of(booking)));

        String result = bookingService.cancel(booking.getName(), booking.getRoom().getRoomNumber());

        assertEquals("Booking cancelled", result);
        verify(bookingRepository, times(1)).save(booking);
        verify(roomRepository).save(booking.getRoom());
        assertTrue(booking.isCancelled());
        assertTrue(booking.getRoom().isAvailable());
    }
}
