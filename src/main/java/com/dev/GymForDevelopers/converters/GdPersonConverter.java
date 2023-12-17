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
public class GdPersonConverter {
    /**
     * Из DTO в Entity
     */
    public GdPerson convertToEntity(GdPersonDTO gdPersonDTO) {

        return GdPerson.builder()
                .age(gdPersonDTO.getAge())
                .country(gdPersonDTO.getCountry())
                .email(gdPersonDTO.getEmail())
                .birthDate(gdPersonDTO.getBirthDate())
                .name(gdPersonDTO.getName())
                .extraInfo(gdPersonDTO.getExtraInfo())
                .build();
    }

    /**
     * из Entity в DTO
     */
    public GdPersonDTO convertToPersonDTO(GdPerson gdPerson) {
        return GdPersonDTO.builder()
                .age(gdPerson.getAge())
                .name(gdPerson.getName())
                .birthDate(gdPerson.getBirthDate())
                .email(gdPerson.getEmail())
                .country(gdPerson.getCountry())
                .extraInfo(gdPerson.getExtraInfo())
                .build();
    }

}