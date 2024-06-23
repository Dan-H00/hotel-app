package com.example.HotelApp.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookingDto {
    private int[] roomNumber;
    private String name;
    private String date;
    private int stayDays;
    private String time;
}
