package com.example.hotel_app.mapper;

import com.example.hotel_app.dto.RoomDto;
import com.example.hotel_app.entity.Room;
import org.mapstruct.Mapper;

@Mapper
public interface RoomMapper {
    RoomDto roomToRoomDto(Room room);
}
