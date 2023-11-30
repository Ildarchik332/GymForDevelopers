package com.dev.GymForDevelopers.models.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "person")
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@Data
public class GdPerson {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    private String email;

    private Integer age;

    private String country;

    private LocalDate birthDate;
}
