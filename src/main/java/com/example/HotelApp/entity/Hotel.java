package com.example.HotelApp.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Builder
public class Hotel {
    @Id
    private int id;
    private String name;
    private double latitude;
    private double longitude;
}
