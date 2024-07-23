package com.example.hotel_app.controller;

import com.example.hotel_app.dto.FeedbackDtoInput;
import com.example.hotel_app.dto.FeedbackDtoOutput;
import com.example.hotel_app.service.FeedbackService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class FeedbackController {
    private final FeedbackService feedbackService;

    @PostMapping("/feedback")
    public FeedbackDtoOutput saveFeedback(@RequestBody FeedbackDtoInput feedbackDtoInput) {
        return feedbackService.saveFeedback(feedbackDtoInput);
    }
}
