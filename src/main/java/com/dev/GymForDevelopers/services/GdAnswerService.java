package com.dev.GymForDevelopers.services;

import com.dev.GymForDevelopers.exceptions.ExceptionConst;
import com.dev.GymForDevelopers.exceptions.GdRuntimeException;
import com.dev.GymForDevelopers.models.entity.GdAnswer;
import com.dev.GymForDevelopers.models.entity.GdQuestion;
import com.dev.GymForDevelopers.repositories.GdAnswerRepository;
import com.dev.GymForDevelopers.repositories.GdQuestionRepository;
import com.dev.GymForDevelopers.listner.AppStartedListener;
import com.dev.GymForDevelopers.util.ValueLikes;
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
     * @param gdanswer Данные ответа
     */
    public void save(Long questionId, GdAnswer gdanswer) {

        if (gdanswer == null) {
            throw new GdRuntimeException(ExceptionConst.MESSAGE_RT, ExceptionConst.ERRORS_CODE_RT);
        }

        GdQuestion question = gdQuestionRepository.findById(questionId);
        if (question == null) {
            throw new GdRuntimeException(ExceptionConst.MESSAGE_NF, ExceptionConst.ERRORS_CODE_NF);
        }

        List<GdAnswer> answerList = question.getAnswer();

        answerList.add(gdanswer);
        gdanswer.setQuestion(question);

        gdQuestionRepository.save(question);
        gdAnswerRepository.save(gdanswer);
    }

    /**
     * Лайк ответа
     *
     * @param id Идентификатор ответа
     * @return Текущее количество лайков
     */
    public Long like(Long id) {
        ValueLikes currentLikes = AppStartedListener.mapAnswerLikes.get(id);

        Long endValue = currentLikes.setEndValue(currentLikes.getEndValue() + 1);

        AppStartedListener.mapNoteDislikes.put(id, new ValueLikes(currentLikes.getStartValue(), endValue));

        return endValue;
    }


}

