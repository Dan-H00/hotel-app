package com.example.HotelApp.repository;

import com.example.HotelApp.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {
    Booking findByName(String name);
    List<Booking> findAllByName(String name);
    Booking findByNameAndRoom_RoomNumber(String name, int roomNumber);
}
