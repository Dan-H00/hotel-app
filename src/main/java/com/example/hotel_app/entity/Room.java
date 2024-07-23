package com.example.hotel_app.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

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
    @ColumnDefault("true")
    private boolean isAvailable;
    @ManyToOne
    @JoinColumn(name = "hotel_id")
    private Hotel hotel;
}
