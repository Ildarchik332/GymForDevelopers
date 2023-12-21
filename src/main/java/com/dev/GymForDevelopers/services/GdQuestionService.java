package com.dev.GymForDevelopers.services;

import com.dev.GymForDevelopers.enums.QuestionSections;
import com.dev.GymForDevelopers.exceptions.ExceptionConst;
import com.dev.GymForDevelopers.exceptions.GdRuntimeException;
import com.dev.GymForDevelopers.models.entity.GdQuestion;
import com.dev.GymForDevelopers.repositories.GdQuestionRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Сервис для работы с вопросами
 *
 * @author Ildar
 */
@Service
@Slf4j
public class GdQuestionService {
    private final GdQuestionRepository gdQuestionRepository;

    @Autowired
    public GdQuestionService(GdQuestionRepository gdQuestionRepository) {
        this.gdQuestionRepository = gdQuestionRepository;
    }

    /**
     * Метод для создания вопроса
     *
     * @param gdQuestion Данные вопроса
     */
    public void save(GdQuestion gdQuestion) {
        if (gdQuestion == null) {
            throw new GdRuntimeException(ExceptionConst.MESSAGE_RT, ExceptionConst.ERRORS_CODE_RT);
        }
        gdQuestionRepository.save(gdQuestion);
    }

    /**
     * Метод для получения случайных вопросов
     *
     * @param count Количество вопросов
     */
    public List<String> getRandomQuestion(Integer count) {
        if (count == null) {
            log.info("Вы не указали колличество вопросов. По-умолчанию выдано 10");
            count = 10;
        }
        return gdQuestionRepository.getRandomQuestion(count);
    }

    /**
     * Метод для получения случайных вопросов из конкретного раздела
     *
     * @param section Раздел вопросов
     * @param count   Количество вопросов
     */
    public List<String> getRandomBySection(String section, Integer count) {
        if (count == null) {
            log.info("Вы не указали колличество вопросов. По-умолчанию выдано 10");
            count = 10;
        }
        if (section == null) {
            log.info("Не указан раздел. Вопросы выдаются из случайных разделов");
            return gdQuestionRepository.getRandomQuestion(count);
        }
        boolean sectionExist = false;
        QuestionSections[] sectionsList = QuestionSections.values();
        for (QuestionSections x : sectionsList) {
            if (section.equals(x.getSection())) {
                sectionExist = true;
                break;
            }
        }
        if (!sectionExist) {
            log.error("Ошибка. Введенный раздел не был найден");
            throw new GdRuntimeException(ExceptionConst.MESSAGE_NF, ExceptionConst.ERRORS_CODE_NF);
        }
        return gdQuestionRepository.getRandomFromSection(section, count);
    }
}

