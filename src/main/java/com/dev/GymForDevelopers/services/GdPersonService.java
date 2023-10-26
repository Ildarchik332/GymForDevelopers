package com.dev.GymForDevelopers.services;

import com.dev.GymForDevelopers.exceptions.GdNotFoundException;
import com.dev.GymForDevelopers.models.entity.GdPerson;
import com.dev.GymForDevelopers.repositories.GdPersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

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
     * @param Person Данные пользователя
     */
    public void save(GdPerson Person) {
        if(Person == null){
            throw  new RuntimeException("В качестве парметра был передан null");
        }
    gdPersonRepository.save(
            GdPerson.builder()
                    .name(Person.getName())
                    .age(Person.getAge())
                    .email(Person.getEmail())
                    .birthDate(Person.getBirthDate())
                    .country(Person.getCountry())
                    .build()
    );
    }

    public GdPerson findOne(long id){
        Optional<GdPerson> findPerson = gdPersonRepository.findById(id);
        return findPerson.orElseThrow(GdNotFoundException::new);
    }

}
