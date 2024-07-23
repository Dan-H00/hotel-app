package com.example.hotel_app.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RoomDto {
    private int id;
    private int roomNumber;
    private double price;
}
