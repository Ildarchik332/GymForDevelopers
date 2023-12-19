package com.dev.GymForDevelopers.models.entity;

import com.google.gson.Gson;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
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
    private Long id;

    private String name;

    private String email;

    private Integer age;

    private String country;

    private LocalDate birthDate;

    @Column(columnDefinition = "jsonb")
    @Convert(converter = ExtraInfoConverter.class)
    private ExtraInfo extraInfo;


    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder(toBuilder = true)
    public static class ExtraInfo implements Serializable {
        public String hobby;

        public String programmingLanguage;

        public String aboutMe;
    }

    @Converter(autoApply = true)
    public static class ExtraInfoConverter implements AttributeConverter<ExtraInfo, String> {

        private final static Gson GSON = new Gson();

        @Override
        public String convertToDatabaseColumn(ExtraInfo extraInfo) {
            return GSON.toJson(extraInfo);
        }

        @Override
        public ExtraInfo convertToEntityAttribute(String s) {
            return GSON.fromJson(s, ExtraInfo.class);
        }
    }
}
