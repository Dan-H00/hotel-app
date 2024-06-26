package com.example.HotelApp.mapper;

import com.example.HotelApp.dto.BookingDto;
import com.example.HotelApp.entity.Booking;
import org.mapstruct.Mapper;

@Mapper
public interface BookingMapper {
    BookingDto bookingToBookingDto(Booking booking);
    Booking bookingDtoToBooking(BookingDto bookingDto);
}
