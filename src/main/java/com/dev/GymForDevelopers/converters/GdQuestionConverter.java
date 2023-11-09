package com.dev.GymForDevelopers.converters;

import com.dev.GymForDevelopers.models.DTO.GdQuestionDTO;
import com.dev.GymForDevelopers.models.entity.GdQuestion;
import org.springframework.stereotype.Service;

/**
 * Сервис для конвертации Entity в DTO и наоборот(Вопрос)
 *
 * @author Ildar
 */
@Service
public class GdQuestionConverter {
    /**
     * Из DTO в Entity
     */
    public GdQuestion convertToEntity(GdQuestionDTO gdQuestionDTO) {
        return GdQuestion.builder()
                .issue(gdQuestionDTO.getIssue())
                .section(gdQuestionDTO.getSection())
                .whoAsked(gdQuestionDTO.getWhoAsked())
                .build();
    }

    /**
     * из Entity в DTO
     */
    public GdQuestionDTO convertToDTO(GdQuestion gdQuestion) {
        return GdQuestionDTO.builder()
                .issue(gdQuestion.getIssue())
                .section(gdQuestion.getSection())
                .whoAsked(gdQuestion.getWhoAsked())
                .build();
    }
}
