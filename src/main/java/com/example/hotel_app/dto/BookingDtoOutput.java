package com.example.hotel_app.dto;

import com.example.hotel_app.entity.Room;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BookingDtoOutput {
    private Long id;
    private String name;
    private LocalTime checkInTime;
    private LocalDate checkInDate;
    private int stayDuration;
    private boolean cancelled;
    private Room room;
}
