package com.example.HotelApp.repository;

import com.example.HotelApp.entity.Hotel;
import com.example.HotelApp.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {
    Room findByRoomNumber(int roomNumber);

    List<Room> findAllByHotel(Hotel hotel);
}
