package com.dev.GymForDevelopers.models.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "answer")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class GdAnswer {
    @Id
    private long question_id;

    private String response;

}
