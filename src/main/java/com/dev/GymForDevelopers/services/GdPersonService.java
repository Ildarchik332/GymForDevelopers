package com.dev.GymForDevelopers.services;

import com.dev.GymForDevelopers.exceptions.ExceptionConst;
import com.dev.GymForDevelopers.exceptions.GdNotFoundException;
import com.dev.GymForDevelopers.exceptions.GdRuntimeException;
import com.dev.GymForDevelopers.models.entity.GdPerson;
import com.dev.GymForDevelopers.repositories.GdPersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Сервис для работы с пользоваетлем
 *
 * @author Ildar
 */
@Service
public class GdPersonService {
    private final GdPersonRepository gdPersonRepository;

    @Autowired
    public GdPersonService(GdPersonRepository gdPersonRepository) {
        this.gdPersonRepository = gdPersonRepository;
    }

    /**
     * Метод для создания пользователя
     *
     * @param person Данные пользователя
     */
    public void save(GdPerson person) {
        if (person == null) {
            throw new GdRuntimeException(ExceptionConst.MESSAGE_RT, ExceptionConst.ERRORS_CODE_RT);
        }
        gdPersonRepository.save(person);
    }

    /**
     * Метод для поиска пользователя по id
     *
     * @param id
     */
    public GdPerson findOne(long id) {
        return gdPersonRepository.findById(id)
                .orElseThrow(() -> {
                    throw new GdNotFoundException(ExceptionConst.MESSAGE_NF, ExceptionConst.ERRORS_CODE_NF);
                });
    }

}
