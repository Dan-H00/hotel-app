package com.example.hotel_app.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.*;

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
}
