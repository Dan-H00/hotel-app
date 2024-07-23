package com.example.hotel_app.service;

import com.example.hotel_app.dto.FeedbackDtoInput;
import com.example.hotel_app.dto.FeedbackDtoOutput;
import com.example.hotel_app.entity.Feedback;
import com.example.hotel_app.mapper.FeedbackMapper;
import com.example.hotel_app.repository.FeedbackRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class FeedbackServiceTest {
    @Mock
    private FeedbackRepository feedbackRepository;

    @Mock
    private FeedbackMapper feedbackMapper;

    @InjectMocks
    private FeedbackService feedbackService;

    @Test
    public void testAddFeedback() {
        FeedbackDtoInput feedbackDtoInput = FeedbackDtoInput.builder()
                .content("test message")
                .name("test")
                .build();

        Feedback feedback = Feedback.builder()
                .id(1L)
                .content(feedbackDtoInput.getContent())
                .name(feedbackDtoInput.getName())
                .build();

        FeedbackDtoOutput feedbackDtoOutput = FeedbackDtoOutput.builder()
                .id(feedback.getId())
                .content(feedback.getContent())
                .name(feedback.getName())
                .build();

        when(feedbackMapper.feedbackDtoInputToFeedback(feedbackDtoInput)).thenReturn(feedback);
        when(feedbackRepository.save(feedback)).thenReturn(feedback);
        when(feedbackMapper.feedbackToFeedbackDtoOutput(feedback)).thenReturn(feedbackDtoOutput);

        FeedbackDtoOutput result = feedbackService.saveFeedback(feedbackDtoInput);
        assertEquals("test message test", result.getContent() + " " + result.getName());

        verify(feedbackRepository).save(feedback);
    }
}
