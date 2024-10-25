package com.example.hotel_app.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Builder
public class Hotel {
    @Id
    @GeneratedValue
    private int id;
    private String name;
    private double latitude;
    private double longitude;
    private String city;
    @OneToMany(mappedBy = "hotel")
    private List<Room> rooms;
}
