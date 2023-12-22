package com.dev.GymForDevelopers.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum QuestionSections {
    HIBERNATE(1, "Hibernate", "Основные вопросы про Hibernate"),
    SPRING(2, "Spring", "Вопросы и нюансы про Spring в целом"),
    JAVA(3, "Java", "Тонкости языка программирования Java"),
    SQL(4, "SQL", "Раздел вопросов про SQL"),
    PATTERNS(5, "Patterns", "Раздел вопросов про Patterns");

    private final Integer code;
    private final String section;
    private final String description;

}
