package com.dev.GymForDevelopers.converters;

import com.dev.GymForDevelopers.models.DTO.GdPersonDTO;
import com.dev.GymForDevelopers.models.entity.GdPerson;
import org.springframework.stereotype.Service;

/**
 * Сервис для конвертации Entity в DTO и наоборот(пользователь)
 *
 * @author Ildar
 */
@Service
public class GdConverterPerson {
    /**
     * Из DTO в Entity
     */
    public GdPerson convertToEntity(GdPersonDTO personDTO) {

        return GdPerson.builder()
                .age(personDTO.getAge())
                .country(personDTO.getCountry())
                .email(personDTO.getEmail())
                .birthDate(personDTO.getBirthDate())
                .name(personDTO.getName())
                .build();
    }

    /**
     * из Entity в DTO
     */
    public GdPersonDTO convertToPersonDTO(GdPerson person) {
        return GdPersonDTO.builder()
                .age(person.getAge())
                .name(person.getName())
                .birthDate(person.getBirthDate())
                .email(person.getEmail())
                .country(person.getCountry())
                .build();
    }

}