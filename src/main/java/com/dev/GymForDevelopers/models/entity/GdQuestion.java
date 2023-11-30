package com.dev.GymForDevelopers.models.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "question")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class GdQuestion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String issue;

    private String section;

    private String whoAsked;

    @OneToMany(mappedBy = "question", fetch = FetchType.EAGER)
    private List<GdAnswer> answer;

}
