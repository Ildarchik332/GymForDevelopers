package com.dev.GymForDevelopers.services;

import com.dev.GymForDevelopers.exceptions.ExceptionConst;
import com.dev.GymForDevelopers.exceptions.GdRuntimeException;
import com.dev.GymForDevelopers.models.entity.GdAnswer;
import com.dev.GymForDevelopers.repositories.GdAnswerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Сервис для работы с ответами
 *
 * @author Ildar
 */

@Service
public class GdAnswerService {
    private final GdAnswerRepository gdAnswerRepository;

    @Autowired
    public GdAnswerService(GdAnswerRepository gdAnswerRepository) {
        this.gdAnswerRepository = gdAnswerRepository;
    }

    /**
     * Метод для создания администратора
     *
     * @param answer Данные администратора
     */
    public void save(GdAnswer answer) {
        if (answer == null) {
            throw new GdRuntimeException(ExceptionConst.MESSAGE_RT, ExceptionConst.ERRORS_CODE_RT);
        }
        gdAnswerRepository.save(
                GdAnswer.builder()
                        .response(answer.getResponse())
                        .question(answer.getQuestion())
                        .whoAnswered(answer.getWhoAnswered())
                        .build()
        );
    }


}
