package com.dev.GymForDevelopers.models.DTO;

import lombok.Builder;
import lombok.Data;
@Data
@Builder(toBuilder = true)
public class GdQuestionDTO {

    private String issue;

    private String section;

    private String whoAsked;

}
