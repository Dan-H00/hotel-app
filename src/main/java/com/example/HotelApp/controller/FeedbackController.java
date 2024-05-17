package com.example.HotelApp.controller;

import com.example.HotelApp.entity.Feedback;
import com.example.HotelApp.repository.FeedbackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FeedbackController {
    @Autowired
    private FeedbackRepository feedbackRepository;

    @PostMapping("/feedback")
    public String saveFeedback(@RequestParam String message, @RequestParam String name) {
        Feedback feedback = new Feedback().builder()
                .content(message)
                .name(name)
                .build();
        feedbackRepository.save(feedback);

        return "Feedback saved successfully";
    }
}
