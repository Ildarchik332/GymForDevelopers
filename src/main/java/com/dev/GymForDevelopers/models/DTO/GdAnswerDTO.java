package com.dev.GymForDevelopers.models.DTO;

import lombok.Builder;
import lombok.Data;

@Data
@Builder(toBuilder = true)
public class GdAnswerDTO {
    private String response;
}
