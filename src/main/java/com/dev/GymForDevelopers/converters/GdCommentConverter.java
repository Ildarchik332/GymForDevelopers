package com.dev.GymForDevelopers.converters;

import com.dev.GymForDevelopers.models.DTO.GdCommentDTO;
import com.dev.GymForDevelopers.models.entity.GdComment;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * Сервис для конвертации Entity в DTO и наоборот(Комментарий)
 *
 * @author Ildar
 */
@Service
public class GdCommentConverter {
    /**
     * Из DTO в Entity
     */
    public GdComment convertToEntity(GdCommentDTO gdCommentDTO) {
        return GdComment.builder()
                .author(gdCommentDTO.getAuthor())
                .note(gdCommentDTO.getNote())
                .text(gdCommentDTO.getText())
                .build();
    }

    /**
     * из Entity в DTO
     */
    public GdCommentDTO convertToDTO(GdComment gdComment) {
        return GdCommentDTO.builder()
                .author(gdComment.getAuthor())
                .text(gdComment.getText())
                .dateOfCreation(gdComment.getDateOfCreation())
                .build();
    }
}
