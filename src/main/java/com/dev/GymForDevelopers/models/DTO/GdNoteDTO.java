package com.dev.GymForDevelopers.models.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GdNoteDTO {

    private String section;

    private String advice;

    private String whoCreated;

    private LocalDate dateOfCreation;

    private Integer status;

    private String number;

    private Long likes;

    private Long dislikes;

    public GdNoteDTO(BuilderDTO builderDTO) {
        this.section = builderDTO.section;
        this.advice = builderDTO.advice;
        this.whoCreated = builderDTO.whoCreated;
        this.dateOfCreation = builderDTO.dateOfCreation;
        this.status = builderDTO.status;
        this.number = builderDTO.number;
        this.likes = builderDTO.likes;
        this.dislikes = builderDTO.dislikes;
    }

    public static BuilderDTO newBuilderDTO() {
        return new BuilderDTO();
    }

    public static class BuilderDTO {
        private String section;
        private String advice;
        private LocalDate dateOfCreation;
        private String whoCreated;
        private Integer status;
        private String number;
        private Long likes;
        private Long dislikes;

        public BuilderDTO section(String section) {
            this.section = section;
            return this;
        }

        public BuilderDTO advice(String advice) {
            this.advice = advice;
            return this;
        }

        public BuilderDTO dataOfCreation(LocalDate dateOfCreation) {
            this.dateOfCreation = dateOfCreation;
            return this;
        }

        public BuilderDTO whoCreated(String whoCreated) {
            this.whoCreated = whoCreated;
            return this;
        }
        public BuilderDTO status(Integer status){
            this.status = status;
            return this;
        }
        public BuilderDTO number(String number){
            this.number = number;
            return this;
        }
        public BuilderDTO likes(Long likes){
            this.likes = likes;
            return this;
        }
        public BuilderDTO dislikes(Long dislikes){
            this.dislikes = dislikes;
            return this;
        }

        public GdNoteDTO build() {
            return new GdNoteDTO(this);
        }
    }
}

