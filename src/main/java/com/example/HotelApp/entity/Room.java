package com.example.HotelApp.entity;

import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Builder
public class Room {
    @Id
    @GeneratedValue
    private int id;
    private int roomNumber;
    private int type;
    private double price;
    private boolean isAvailable;
    @ManyToOne
    @JoinColumn(name = "hotel_id")
    private Hotel hotel;
}
