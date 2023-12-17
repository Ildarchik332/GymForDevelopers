package com.dev.GymForDevelopers.models.DTO;

import com.dev.GymForDevelopers.models.entity.GdPerson;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder(toBuilder = true)
public class GdPersonDTO {
    private String name;

    private String email;

    private Integer age;

    private String country;

    private LocalDate birthDate;

    private GdPerson.ExtraInfo extraInfo;
}
