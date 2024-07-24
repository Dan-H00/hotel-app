package com.example.hotel_app.mapper;

import com.example.hotel_app.dto.BookingDtoInput;
import com.example.hotel_app.dto.BookingDtoOutput;
import com.example.hotel_app.entity.Booking;
import org.mapstruct.Mapper;

@Mapper
public interface BookingMapper {
    BookingDtoOutput bookingToBookingDtoOutput(Booking booking);
    BookingDtoInput bookingToBookingDtoInput(Booking booking);
    Booking bookingDtoInputToBooking(BookingDtoInput bookingDtoInput);
    Booking bookingDtoOutputToBooking(BookingDtoOutput bookingDtoOutput);
}
