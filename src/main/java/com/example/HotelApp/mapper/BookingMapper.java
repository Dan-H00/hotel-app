package com.example.HotelApp.mapper;

import com.example.HotelApp.dto.BookingDto;
import com.example.HotelApp.entity.Booking;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface BookingMapper {
    BookingMapper INSTANCE = Mappers.getMapper(BookingMapper.class);
    BookingDto bookingToBookingDto(Booking booking);
    Booking bookingDtoToBooking(BookingDto bookingDto);
}
