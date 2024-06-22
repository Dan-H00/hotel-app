package com.example.HotelApp.service;

import com.example.HotelApp.entity.Feedback;
import com.example.HotelApp.repository.FeedbackRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FeedbackService {
    private final FeedbackRepository feedbackRepository;

    public String saveFeedback(String message, String name) {
        Feedback feedback = new Feedback().builder()
                .content(message)
                .name(name)
                .build();
        feedbackRepository.save(feedback);

        return "Feedback saved successfully";
    }
}
