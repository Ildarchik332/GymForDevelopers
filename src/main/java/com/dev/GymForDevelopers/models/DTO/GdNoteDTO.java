package com.dev.GymForDevelopers.models.DTO;

import lombok.Builder;
import lombok.Data;

@Data
@Builder(toBuilder = true)
public class GdNoteDTO {

    private String section;

    private String advice;

    private String whoCreated;

}