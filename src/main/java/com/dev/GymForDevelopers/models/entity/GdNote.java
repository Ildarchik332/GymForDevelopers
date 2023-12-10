package com.dev.GymForDevelopers.models.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "note")
@Getter
@Setter
@RequiredArgsConstructor
public class GdNote {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String section;

    private String advice;

    private LocalDate dateOfCreation;

    private String whoCreated;

    private Integer status;

    private String number;

    private Long likes;

    private Long dislikes;

    @OneToMany(mappedBy = "note", fetch = FetchType.EAGER)
    @JsonManagedReference
    private List<GdComment> comments;


    public GdNote(BuilderGdNote builderGdNote) {
        this.id = builderGdNote.id;
        this.section = builderGdNote.section;
        this.advice = builderGdNote.advice;
        this.dateOfCreation = builderGdNote.dateOfCreation;
        this.whoCreated = builderGdNote.whoCreated;
        this.comments = builderGdNote.comments;
        this.status = builderGdNote.status;
        this.number = builderGdNote.number;
        this.likes = builderGdNote.likes;
        this.dislikes = builderGdNote.dislikes;
    }

    public static BuilderGdNote newBuilder() {
        return new BuilderGdNote();
    }


    public static class BuilderGdNote {
        private Long id;
        private String section;
        private String advice;
        private LocalDate dateOfCreation;
        private String whoCreated;
        private List<GdComment> comments;
        private Integer status;
        private String number;
        private Long likes;
        private Long dislikes;

        public BuilderGdNote id(Long id) {
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

        public BuilderGdNote comments(List<GdComment> comments) {
            this.comments = comments;
            return this;
        }

        public BuilderGdNote status(Integer status) {
            this.status = status;
            return this;
        }

        public BuilderGdNote number(String number) {
            this.number = number;
            return this;
        }
        public BuilderGdNote likes (Long likes){
            this.likes = likes;
            return this;
        }
        public BuilderGdNote dislikes(Long dislikes){
            this.dislikes = dislikes;
            return this;
        }

        public GdNote build() {
            return new GdNote(this);
        }


    }
}
