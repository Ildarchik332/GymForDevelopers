package com.dev.GymForDevelopers.models.DTO;

import com.dev.GymForDevelopers.models.entity.GdQuestion;
import lombok.Builder;
import lombok.Data;

@Data
@Builder(toBuilder = true)
public class GdAnswerDTO {
    private Long questionId;

    private String response;

    private GdQuestion question;

    private String whoAnswered;
}
