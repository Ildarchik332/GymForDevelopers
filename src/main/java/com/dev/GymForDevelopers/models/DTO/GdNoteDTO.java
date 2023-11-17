package com.dev.GymForDevelopers.models.DTO;

import com.dev.GymForDevelopers.enums.StatusEnum;
import com.fasterxml.jackson.databind.EnumNamingStrategies;
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

    public GdNoteDTO(BuilderDTO builderDTO) {
        this.section = builderDTO.section;
        this.advice = builderDTO.advice;
        this.whoCreated = builderDTO.whoCreated;
        this.dateOfCreation = builderDTO.dateOfCreation;
        this.status = builderDTO.status;
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

        public GdNoteDTO build() {
            return new GdNoteDTO(this);
        }
    }
}

