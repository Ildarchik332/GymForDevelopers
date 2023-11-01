package com.dev.GymForDevelopers.models.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "note")
@Data
public class GdNote {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String section;

    private String advice;

    private LocalDate dateOfCreation;

    private String whoCreated;

    public GdNote(BuilderGdNote builderGdNote) {
        this.id = builderGdNote.id;
        this.section = builderGdNote.section;
        this.advice = builderGdNote.advice;
        this.dateOfCreation = builderGdNote.dateOfCreation;
        this.whoCreated = builderGdNote.whoCreated;
    }

    public static BuilderGdNote newBuilder() {
        return new BuilderGdNote();
    }


    public static class BuilderGdNote {
        private Integer id;
        private String section;
        private String advice;
        private LocalDate dateOfCreation;
        private String whoCreated;

        public BuilderGdNote id(Integer id) {
            this.id = id;
            return this;
        }

        public BuilderGdNote section(String section) {
            this.section = section;
            return this;
        }

        public BuilderGdNote advice(String advice) {
            this.advice = advice;
            return this;
        }

        public BuilderGdNote dateOfCreation(LocalDate dateOfCreation) {
            this.dateOfCreation = dateOfCreation;
            return this;
        }

        public BuilderGdNote whoCreated(String whoCreated) {
            this.whoCreated = whoCreated;
            return this;
        }

        public GdNote build() {
            return new GdNote(this);
        }


    }
}
