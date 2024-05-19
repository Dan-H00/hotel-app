package com.example.HotelApp.entity;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

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
