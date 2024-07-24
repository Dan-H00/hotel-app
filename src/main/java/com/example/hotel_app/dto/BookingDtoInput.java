package com.example.hotel_app.dto;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BookingDtoInput {
    private int[] roomNumber;
    private String name;
    private LocalDate checkInDate;
    private int stayDuration;
    private String checkInTime;
}
