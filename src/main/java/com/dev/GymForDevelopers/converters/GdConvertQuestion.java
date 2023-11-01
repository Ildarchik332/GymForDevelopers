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
public class GdConvertQuestion {
    /**
     * Из DTO в Entity
     */
    public GdQuestion convertToEntity(GdQuestionDTO DTO) {
        return GdQuestion.builder()
                .issue(DTO.getIssue())
                .section(DTO.getSection())
                .whoAsked(DTO.getWhoAsked())
                .build();
    }

    /**
     * из Entity в DTO
     */
    public GdQuestionDTO convertToDTO(GdQuestion question) {
        return GdQuestionDTO.builder()
                .issue(question.getIssue())
                .section(question.getSection())
                .whoAsked(question.getWhoAsked())
                .build();
    }
}
