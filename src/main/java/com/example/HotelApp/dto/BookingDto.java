package com.example.HotelApp.dto;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BookingDto {
    private int[] roomNumber;
    private String name;
    private LocalDate checkInDate;
    private int stayDuration;
    private String checkInTime;
}
