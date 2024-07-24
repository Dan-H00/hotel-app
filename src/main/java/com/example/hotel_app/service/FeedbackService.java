package com.example.hotel_app.service;

import com.example.hotel_app.dto.FeedbackDtoInput;
import com.example.hotel_app.dto.FeedbackDtoOutput;
import com.example.hotel_app.entity.Feedback;
import com.example.hotel_app.mapper.FeedbackMapper;
import com.example.hotel_app.repository.FeedbackRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FeedbackService {
    private final FeedbackRepository feedbackRepository;
    private final FeedbackMapper feedbackMapper;

    public FeedbackDtoOutput saveFeedback(FeedbackDtoInput feedbackDtoInput) {
        Feedback feedback = feedbackMapper.feedbackDtoInputToFeedback(feedbackDtoInput);
        Feedback outputFeedback = feedbackRepository.save(feedback);

        FeedbackDtoOutput feedbackDtoOutput = feedbackMapper.feedbackToFeedbackDtoOutput(outputFeedback);

        return feedbackDtoOutput;
    }
}
