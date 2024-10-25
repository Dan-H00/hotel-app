package com.example.hotel_app.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldNameConstants;
import org.hibernate.annotations.ColumnDefault;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Builder
@FieldNameConstants
public class Room {
    @Id
    @GeneratedValue
    private int id;
    private int roomNumber;
    private String type;
    @Column(name = "price_per_night")
    private double pricePerNight;
    @ManyToOne
    @JoinColumn(name = "hotel_id")
    private Hotel hotel;
    private int capacity;
    @OneToMany
    private List<BookedDates> bookedDates;
}
