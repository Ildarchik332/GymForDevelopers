package com.dev.GymForDevelopers.models.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@Entity
@Table(name = "answer")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class GdAnswer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String response;

    private String whoAnswered;

    private Long likes;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "question_id", referencedColumnName = "id")
    private GdQuestion question;



}
