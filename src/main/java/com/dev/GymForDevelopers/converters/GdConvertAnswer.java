package com.dev.GymForDevelopers.converters;

import com.dev.GymForDevelopers.models.DTO.GdAnswerDTO;
import com.dev.GymForDevelopers.models.entity.GdAnswer;
import org.springframework.stereotype.Service;
/**
 * Сервис для конвертации Entity в DTO и наоборот(Ответ)
 *
 * @author Ildar
 */
@Service
public class GdConvertAnswer {
    /**
     * Из DTO в Entity
     */
    public GdAnswer convertToEntity(GdAnswerDTO dto) {
        return GdAnswer.builder()
                .response(dto.getResponse())
                .question(dto.getQuestion())
                .whoAnswered(dto.getWhoAnswered())
                .build();
    }
    /**
     * из Entity в DTO
     */
    public GdAnswerDTO convertToDto(GdAnswer answer) {
        return GdAnswerDTO.builder()
                .response(answer.getResponse())
                .question(answer.getQuestion())
                .whoAnswered(answer.getWhoAnswered())
                .build();
    }

}
