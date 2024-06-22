package com.example.HotelApp.controller;

import com.example.HotelApp.entity.Feedback;
import com.example.HotelApp.repository.FeedbackRepository;
import com.example.HotelApp.service.FeedbackService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class FeedbackController {
    private final FeedbackService feedbackService;

    @PostMapping("/feedback")
    public String saveFeedback(@RequestParam String message, @RequestParam String name) {
        return feedbackService.saveFeedback(message, name);
    }
}
