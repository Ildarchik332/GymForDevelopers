package com.dev.GymForDevelopers.models.DTO;

import com.dev.GymForDevelopers.models.entity.GdNote;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder(toBuilder = true)
public class GdCommentDTO {
    private Integer noteId;

    private String author;

    private GdNote note;

    private LocalDateTime dateOfCreation;

    private String text;

}
