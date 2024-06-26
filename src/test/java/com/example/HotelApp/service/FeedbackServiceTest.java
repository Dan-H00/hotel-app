package com.example.HotelApp.service;

import com.example.HotelApp.entity.Feedback;
import com.example.HotelApp.repository.FeedbackRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class FeedbackServiceTest {
    @Mock
    private FeedbackRepository feedbackRepository;

    @InjectMocks
    private FeedbackService feedbackService;

    @Test
    public void testAddFeedback() {
        String name = "test";
        String message = "test message";

        String result = feedbackService.saveFeedback(message, name);

        assertEquals("Feedback saved successfully", result);
        verify(feedbackRepository).save(Feedback.builder()
                        .name(name)
                        .content(message)
                        .build());
    }
}
