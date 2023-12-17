package com.dev.GymForDevelopers.services;

import com.dev.GymForDevelopers.exceptions.ExceptionConst;
import com.dev.GymForDevelopers.exceptions.GdNotFoundException;
import com.dev.GymForDevelopers.exceptions.GdRuntimeException;
import com.dev.GymForDevelopers.models.entity.GdPerson;
import com.dev.GymForDevelopers.repositories.GdPersonRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Сервис для работы с пользоваетлем
 *
 * @author Ildar
 */
@Service
@Slf4j
public class GdPersonService {
    ObjectMapper objectMapper = new ObjectMapper();

    private final GdPersonRepository gdPersonRepository;

    @Autowired
    public GdPersonService(GdPersonRepository gdPersonRepository) {
        this.gdPersonRepository = gdPersonRepository;
    }

    /**
     * Метод для создания пользователя
     *
     * @param gdPerson Данные пользователя
     */
    public void save(GdPerson gdPerson) {
        if (gdPerson == null) {
            throw new GdRuntimeException(ExceptionConst.MESSAGE_RT, ExceptionConst.ERRORS_CODE_RT);
        }
        gdPersonRepository.save(gdPerson);
    }

    /**
     * Метод для поиска пользователя по id
     *
     * @param id Идентификатор пользователя
     */
    public GdPerson findOne(Long id) {
        return gdPersonRepository.findById(id)
                .orElseThrow(() -> new GdNotFoundException(ExceptionConst.MESSAGE_NF, ExceptionConst.ERRORS_CODE_NF));
    }

    /**
     * Метод для создания/обновления доп. информации об пользоваетеле
     *
     * @param id Идентификатор пользователя
     */
    public void saveInfo(Long id, GdPerson.ExtraInfo extraInfo) {
        if (extraInfo == null || id == null) {
            throw new GdRuntimeException("Невалидные параметры при запросе", "personService.saveExtraInfo.params.null");
        }
        try {
            String s = objectMapper.writeValueAsString(extraInfo);
            gdPersonRepository.saveExtraInfo(id, s);
            log.info("Дополнительная информация создана/обновленна");
        } catch (JsonProcessingException e) {
            log.error("Ошибка конвертации {}", e.getMessage());
            throw new GdRuntimeException("Ошибка в ходе создания/обновления дополнительной информации о пользователе",
                    "personService.saveExtraInfo.failed");
        }
    }

    /**
     * Метод для поиска дополнительной информации пользователя по его индентификатору
     *
     * @param id Идентификатор пользователя
     */
    public GdPerson.ExtraInfo findExtraInfo(Long id) {
        if (id == null) {
            throw new GdRuntimeException(ExceptionConst.MESSAGE_RT, ExceptionConst.ERRORS_CODE_RT);
        }
        GdPerson.ExtraInfo info = gdPersonRepository.getExtraInfoById(id);
        if (info == null) {
            throw new GdRuntimeException(ExceptionConst.MESSAGE_NF, ExceptionConst.ERRORS_CODE_NF);
        }
        return info;
    }


}
