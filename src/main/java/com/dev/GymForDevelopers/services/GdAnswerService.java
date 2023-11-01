package com.dev.GymForDevelopers.services;

import com.dev.GymForDevelopers.exceptions.ExceptionConst;
import com.dev.GymForDevelopers.exceptions.GdRuntimeException;
import com.dev.GymForDevelopers.models.entity.GdAnswer;
import com.dev.GymForDevelopers.models.entity.GdQuestion;
import com.dev.GymForDevelopers.repositories.GdAnswerRepository;
import com.dev.GymForDevelopers.repositories.GdQuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Сервис для работы с ответами
 *
 * @author Ildar
 */

@Service
public class GdAnswerService {
    private final GdAnswerRepository gdAnswerRepository;
    private final GdQuestionRepository gdQuestionRepository;

    @Autowired
    public GdAnswerService(GdAnswerRepository gdAnswerRepository, GdQuestionRepository gdQuestionRepository) {
        this.gdAnswerRepository = gdAnswerRepository;
        this.gdQuestionRepository = gdQuestionRepository;
    }

    /**
     * Метод для создания ответа
     *
     * @param answer Данные ответа
     */
    public void save(Long questionId, GdAnswer answer) {

        if (answer == null) {
            throw new GdRuntimeException(ExceptionConst.MESSAGE_RT, ExceptionConst.ERRORS_CODE_RT);
        }

        GdQuestion question = gdQuestionRepository.findById(questionId);
        if (question == null) {
            throw new GdRuntimeException(ExceptionConst.MESSAGE_NF, ExceptionConst.ERRORS_CODE_NF);
        }

        List<GdAnswer> answerList = question.getAnswer();

        answerList.add(answer);
        answer.setQuestion(question);

        gdQuestionRepository.save(question);
        gdAnswerRepository.save(answer);
    }
}
