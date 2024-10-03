package com.example.hotel_app.repository;

import com.example.hotel_app.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {
    Booking findByCustomerName(String name);
    List<Booking> findAllByCustomerName(String name);
    Booking findByCustomerNameAndRoom_RoomNumber(String name, int roomNumber);
}
