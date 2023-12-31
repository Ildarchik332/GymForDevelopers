package com.dev.GymForDevelopers.models.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
@Table(name = "comment")
public class GdComment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String author;

    private LocalDateTime dateOfCreation;

    private String text;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "note_id", referencedColumnName = "id")
    @JsonBackReference
    private GdNote note;
}
