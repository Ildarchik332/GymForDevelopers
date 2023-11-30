package com.dev.GymForDevelopers.models.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "admin")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class GdAdmin {
    @Id
    @GeneratedValue(generator = "UUID")
    private UUID id;

    private String name;

    private Integer age;

    private LocalDate birthDate;

    private String phoneNumber;

    private String email;


}
