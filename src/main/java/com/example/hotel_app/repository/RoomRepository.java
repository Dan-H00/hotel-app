package com.example.hotel_app.repository;

import com.example.hotel_app.entity.Hotel;
import com.example.hotel_app.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {
    Room findByRoomNumber(int roomNumber);

    List<Room> findAllByHotel(Hotel hotel);
}
