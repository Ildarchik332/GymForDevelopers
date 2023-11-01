package com.dev.GymForDevelopers.services;

import com.dev.GymForDevelopers.exceptions.ExceptionConst;
import com.dev.GymForDevelopers.exceptions.GdRuntimeException;
import com.dev.GymForDevelopers.models.entity.GdQuestion;
import com.dev.GymForDevelopers.repositories.GdQuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Сервис для работы с вопросами
 *
 * @author Ildar
 */
@Service
public class GdQuestionService {
    private final GdQuestionRepository questionRepository;

    @Autowired
    public GdQuestionService(GdQuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    /**
     * Метод для создания вопроса
     *
     * @param question Данные вопроса
     */
    public void save(GdQuestion question) {
        if (question == null) {
            throw new GdRuntimeException(ExceptionConst.MESSAGE_RT, ExceptionConst.ERRORS_CODE_RT);
        }
        questionRepository.save(GdQuestion.builder()
                .issue(question.getIssue())
                .section(question.getSection())
                .whoAsked(question.getWhoAsked())
                .build()
        );
    }
}
