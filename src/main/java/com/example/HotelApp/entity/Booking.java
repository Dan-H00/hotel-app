package com.example.HotelApp.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import java.time.LocalDate;
import java.time.LocalTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Builder
public class Booking {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private LocalTime checkInTime;
    private LocalDate checkInDate;
    private int stayDuration;
    @ColumnDefault("false")
    private boolean cancelled;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "room_id")
    private Room room;
}
