package com.example.hotel_app.mapper;

import com.example.hotel_app.dto.FeedbackDtoInput;
import com.example.hotel_app.dto.FeedbackDtoOutput;
import com.example.hotel_app.entity.Feedback;
import org.mapstruct.Mapper;

@Mapper
public interface FeedbackMapper {
    Feedback feedbackDtoInputToFeedback(FeedbackDtoInput feedbackDtoInput);
    Feedback feedbackDtoOutputToFeedback(FeedbackDtoOutput feedbackDtoOutput);
    FeedbackDtoOutput feedbackToFeedbackDtoOutput(Feedback feedback);
    FeedbackDtoInput feedbackToFeedbackDtoInput(Feedback feedback);
}
