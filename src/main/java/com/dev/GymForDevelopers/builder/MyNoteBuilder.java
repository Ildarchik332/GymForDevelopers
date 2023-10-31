package com.dev.GymForDevelopers.builder;

import com.dev.GymForDevelopers.models.entity.GdNote;

import java.time.LocalDate;

public class MyNoteBuilder {
    public static void main(String[] args) {
        BuilderBuild build = new BuilderBuild();
        GdNote note = build
                .id(1)
                .dateOfCreation(LocalDate.of(2000, 1, 14))
                .build();
        System.out.println(note.getId() + " " + note.getDateOfCreation());


    }

    static class BuilderBuild {
        private Integer id;
        private String section;
        private String advice;
        private LocalDate dateOfCreation;
        private String whoCreated;

        public BuilderBuild id(Integer id){
            this.id = id;
            return this;
        }
        public BuilderBuild section(String section) {
            this.section = section;
            return this;
        }
        public BuilderBuild advice(String advice) {
            this.advice = advice;
            return this;
        }
        public BuilderBuild dateOfCreation(LocalDate dateOfCreation) {
            this.dateOfCreation = dateOfCreation;
            return this;
        }
        public BuilderBuild whoCreated(String whoCreated) {
            this.whoCreated = whoCreated;
            return this;
        }
        public GdNote build(){
            return new GdNote(id, section, advice, dateOfCreation, whoCreated);
        }
    }

}
