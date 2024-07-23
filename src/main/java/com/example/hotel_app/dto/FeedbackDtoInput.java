package com.example.hotel_app.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FeedbackDtoInput {
    private String content;
    private String name;
}
