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
public class GdAnswerConverter {
    /**
     * Из DTO в Entity
     */
    public GdAnswer convertToEntity(GdAnswerDTO gdAnswerDTO) {
        return GdAnswer.builder()
                .response(gdAnswerDTO.getResponse())
                .question(gdAnswerDTO.getQuestion())
                .whoAnswered(gdAnswerDTO.getWhoAnswered())
                .likes(gdAnswerDTO.getLikes())
                .build();
    }

    /**
     * из Entity в DTO
     */
    public GdAnswerDTO convertToDto(GdAnswer gdAnswer) {
        return GdAnswerDTO.builder()
                .response(gdAnswer.getResponse())
                .question(gdAnswer.getQuestion())
                .whoAnswered(gdAnswer.getWhoAnswered())
                .likes(gdAnswer.getLikes())
                .build();
    }

}
