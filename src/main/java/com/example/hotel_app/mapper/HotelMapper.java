package com.example.hotel_app.mapper;

import com.example.hotel_app.dto.HotelDto;
import com.example.hotel_app.entity.Hotel;
import org.mapstruct.Mapper;

@Mapper
public interface HotelMapper {
    HotelDto hotelToHotelDto(Hotel hotel);
}
