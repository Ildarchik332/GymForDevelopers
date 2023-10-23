package com.dev.GymForDevelopers.models.DTO;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
@Data
@Builder(toBuilder = true)
public class AdminDTO {
    private String name;

    private Integer age;

    private LocalDate birthDate;

    private String phoneNumber;

    private String email;

}
